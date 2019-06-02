var leadersTable = null;

function initPage() {
  $.ajaxSetup({
    beforeSend : function(xhr, settings) {
      if (settings.type == 'POST' || settings.type == 'PUT'
          || settings.type == 'DELETE') {
        if (!(/^http:.*/.test(settings.url) || /^https:.*/
            .test(settings.url))) {
          // Only send the token to relative URLs i.e. locally.
          xhr.setRequestHeader("X-XSRF-TOKEN",
              Cookies.get('XSRF-TOKEN'));
        }
      }
    }
  });
  initNavbar();
  $.ajax({
	type: "GET",
    contentType: "application/json; charset=utf-8",
	url: "/user",
	success: function (data) {
	  $("#user").html(data.displayName);
	  $(".unauthenticated").hide();
	  $(".authenticated").show();
	  initHome();
	  initUser();
	  initPokemon();
	  showHome();
	}
  });
}

function initNavbar() {
  $('#lucky-nav-group .btn').on('click', function(event) {
    var val = $(this).find('input').val();
	showView(val);
  });
}

function showView(view) {
	if (view==="HOME") {
		$("#lucky-pokemon-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-leader-page").hide();
		$("#lucky-home-page").show();
	} else if (view==="USER") {
		$("#lucky-home-page").hide();
		$("#lucky-pokemon-page").hide();
		$("#lucky-leader-page").hide();
		$("#lucky-user-page").show();
	} else if (view==="POKEMON") {
		$("#lucky-home-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-leader-page").hide();
		$("#lucky-pokemon-page").show();
	} else if (view==="LEADER") {
		$("#lucky-home-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-pokemon-page").hide();
		$("#lucky-leader-page").show();
		showLeaderboard();
	}
}

function initHome() {
  $('#lucky-filter-group .btn').on('click', function(event) {
	var val = $(this).find('input').val();
    filterPokemon(val);
  });
}

function initUser() {
  $.ajax({
    type: "GET",
    contentType: "application/json; charset=utf-8",
    url: "/user/all",
    success: function (data) {
      $('#user-search').select2({
        placeholder: "Select a trainer",
        width: '90%',
        data: data
      });
      $('#user-search').on("select2:select", function(e) {
    	  $("#user-pokemon-header").show();
    	  $("#user-pokemon").show();
    	  showUserPokemon(e.params.data.id);
      });
    },
    error: function (result) {
      errorPage("Failed to query user data", result);
    }
  });
}

function showUserPokemon(id) {
  $.get("/pokemon/user/"+id, function(data) {
	var width = getImageSize("#user-pokemon");
    $("#user-pokemon").html("");
	var str = "";
	for (var i=0; i<data.length; i++) {
	  str += "<button id=\"user-pokemon-" + data[i].id + "\" class=\"lucky-cell\">";
	  str += getCellHtml(data[i], width);
	  str += "</button>\n";
	}
	$("#user-pokemon").html(str);
  });
}

function initPokemon() {
}

function showHome() {
  $.get("/pokemon", function(data) {
	var width = getImageSize("#pokemon");
    $("#pokemon").html("");
    var str = "";
    for (var i=0; i<data.length; i++) {
      str += "<button id=\"pokemon-" + data[i].id + "\" class=\"lucky-cell\" onclick=\"selectPokemon(" + data[i].id + ")\">";
      str += getCellHtml(data[i], width);
      str += "</button>\n";
    }
    $("#pokemon").html(str);
    resetPercentage();
  });
}

function selectPokemon(id) {
  // find out if already selected  
  var element = "#pokemon-" + id;
  var selected = $(element).find('span').hasClass("lucky-got");
  var data = {
    selected: !selected
  }
  // update it
  $.ajax({
    type: "POST",
	contentType: "application/json; charset=utf-8",
	url: "pokemon/"+id,
	data: JSON.stringify(data),
	success: function (data) {
	  // Update the UI
	  var width = getImageSize("#pokemon");
	  $(element).html("");
      $(element).html(getCellHtml(data, width));
      resetPercentage();
      searchPokemon();
	},
	error: function (result) {
	  errorPage("Failed to update pokemon", result);
	}
  });
}

function getCellHtml(data, width) {
  var str = "";
  var labelClass = "lucky-need";
  var imgClass = "";
  if (data.done===true) {
	labelClass = "lucky-got";
	str += "<img class=\"lucky-cell-tick\" src=\"images/tickmark.png\"></img>";
  }
  var image_url = getImageUrl(data);
  str += "<img src=\"" + image_url + "\" ";
  str += "width=\"" + width + "\" "
  if (data.done!==true) {
	  str += " class=\"lucky-img-outline\"";
  }
  str += "></img>" +
    "<span class=\"lucky-cell-label " + labelClass + "\">" + data.name + "<\span>" +
	"</button>\n";
  return str;
}

function getImageUrl(data) {
  var iconId = "";
  if (data.dexid<10) {
    iconId = "00" + data.dexid;
  } else if (data.dexid<100) {
    iconId = "0" + data.dexid;
  } else {
    iconId = data.dexid;
  }
  image_url = "images/" + 
	"pokemon_icon_" + iconId + "_00.png";
  return image_url;
}

function getImageSize(grid) {
  var divsize = $(grid).width();
  var imgsize = Math.round(divsize / 8);
  if (imgsize > 150) {
	imgsize = 150;
  }
  if (imgsize<10) {
	imgsize = 10;
  }
  return imgsize;
}

function logout() {
  $.post("/logout", function() {
    $("#user").html('');
    $(".unauthenticated").show();
    $(".authenticated").hide();
  })
  return true;
}

function searchUserPokemon() {
  // Declare variables
  var input, filterText, grid, cells, label, i, txtValue;
  input = document.getElementById("lucky-user-searchbox");
  filterText = input.value.toUpperCase();
  grid = document.getElementById("user-pokemon");
  cells = grid.getElementsByClassName("lucky-cell");
		  
  // Loop through all grid rows, and hide those who don't match the search query
  for (i = 0; i < cells.length; i++) {
    label = cells[i].getElementsByClassName("lucky-cell-label")[0];
	if (label) {
	  txtValue = label.textContent || label.innerText;
	  if (txtValue.toUpperCase().indexOf(filterText) > -1) {
	    cells[i].style.display = "";
	  } else {
		cells[i].style.display = "none";
	  }
	}
  }
}

function searchPokemon() {
  // Find which radio button is selected
  var filter = $('#lucky-filter-group .active input').val();
  filterPokemon(filter);
}

function filterPokemon(filter) {
  // Declare variables
  var input, filterText, grid, cells, label, i, txtValue;
  input = document.getElementById("lucky-searchbox");
  filterText = input.value.toUpperCase();
  grid = document.getElementById("pokemon");
  cells = grid.getElementsByClassName("lucky-cell");
  
  // Loop through all grid rows, and hide those who don't match the search query
  for (i = 0; i < cells.length; i++) {
    label = cells[i].getElementsByClassName("lucky-cell-label")[0];
	if (label) {
	  txtValue = label.textContent || label.innerText;
	  if (txtValue.toUpperCase().indexOf(filterText) > -1) {
	    if (filter=="ALL") {
	      cells[i].style.display = "";
	    } else if (filter=="NEED" && label.classList.contains("lucky-need")) {
	      cells[i].style.display = "";
	    } else if (filter=="GOT" && !label.classList.contains("lucky-need")) {
	      cells[i].style.display = "";
	    } else {
	      cells[i].style.display = "none";
	    }
	  } else {
	    cells[i].style.display = "none";
	  }
	}
  }
}

function resetPercentage() {
  $.get("/user/stats", function(data) {
	  var colors = getPercentageColors();
	  // Clear previous percentage
	  $('#lucky-gold-percent').html('');
	  // Draw the new percentage
	  var el = document.getElementById('lucky-gold-percent');
	  drawPercentage(el, 42, 5, data.amount, data.total, colors.background, colors.gold, true, false);
  });
}

function getPercentageColors() {
  var backColor = '#efefef';
  var goldColor = '#f0f000';
  var retData = {
	background: backColor,
    gold: goldColor
  };
  return retData;
}

function showLeaderboard() {
  $('#leadersTableBody').html("Loading...");
  $.ajax({
    type: "GET",
    contentType: "application/json; charset=utf-8",
    url: "/user/leaderboard",
    success: function (data) {
      resetLeadersTable();
      $('#leadersTableBody').html("");
      if (data == null || data.length==0) {
        $('#leadersTableBody').append("<tr><td><div class=\"alert alert-warning\" role=\"alert\">No leaderboard to display.</div></td></tr>");
      } else {
        for (var i = 0; i < data.length; i++) {
      	  var rankClass = getRankClass(data[i].rank);
      	  $('#leadersTableBody').append("<tr>" +
      	    "<td class=\"" + rankClass + "\">" + getRankHtml(data[i].rank) + "</td>" +
      	    "<td class=\"" + rankClass + "\">"  + data[i].name + "</td>" +
      	    "<td class=\"" + rankClass + " lucky-lead-total\">"  + data[i].total + "</td>");
      	  }
      	  // Initialize the table
          leadersTable = $('#leadersTable').DataTable({
            "autoWidth": true,
          	"scrollY": 300,
          	"scrollX": true,
          	"searching": false,
          	"lengthChange": false
          });
        }
     },
     error: function (result) {
       resetLeadersTable();
       $('#leadersTableBody').html("");
       var errorHtml = "<tr><td><div class=\"alert alert-danger\" role=\"alert\">Failed to query leaderboard.";
       if (result.responseJSON !== undefined) {
         errorHtml += "<br>" + result.responseJSON.message;
       }
       errorHtml += "</div></td></tr>";
       $('#leadersTableBody').append(errorHtml);
     },
     complete: function() {
       if (leadersTable != null) {
         leadersTable.columns.adjust().draw();
       }
     }
  });
}

function getRankClass(rank) {
  if (rank==1) {
    return "lucky-rank-1";
  }
  if (rank==2) {
    return "lucky-rank-2";
  }
  if (rank==3) {
    return "lucky-rank-3";
  }
  return "lucky-rank-other";
}

function getRankHtml(rank) {
  if (rank==1) {
    return "<span class=\"lucky-lead-medal1\"></span>";
  }
  if (rank==2) {
    return "<span class=\"lucky-lead-medal2\"></span>";
  }
  if (rank==3) {
    return "<span class=\"lucky-lead-medal3\"></span>";
  }
  return ""+rank;
}

function resetLeadersTable() {
  if (leadersTable != null) {
    leadersTable.destroy();
    leadersTable = null;
  }
}

function errorPage(title, results) {
  $('#errorPageTitle').text(title);
  if (results.responseJSON !== undefined) {
    $('#errorPageBody').text(results.responseJSON.message);
  }
  $('#errorPage').modal('show');
}