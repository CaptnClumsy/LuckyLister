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
		$("#lucky-home-page").show();
	} else if (view==="USER") {
		$("#lucky-home-page").hide();
		$("#lucky-pokemon-page").hide();
		$("#lucky-user-page").show();
	} else if (view==="POKEMON") {
		$("#lucky-home-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-pokemon-page").show();
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
	  $(element).html("");
      $(element).html(getCellHtml(data));
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
  var filter = $('#lucky-filter-group input:radio:checked').val()
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

function errorPage(title, results) {
  $('#errorPageTitle').text(title);
  if (results.responseJSON !== undefined) {
    $('#errorPageBody').text(results.responseJSON.message);
  }
  $('#errorPage').modal('show');
}