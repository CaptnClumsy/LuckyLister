var leadersTable = null;
var userSelect = null;
var page_mode = "";
var api_url = "";
var current_view = "HOME";
var include_hats = true;

function initPage(mode) {
  if (mode!="lucky") {
	  page_mode = mode;
	  api_url = "/" + mode;
  }
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
	},
	error: function (data) {
	  if (data.status==500) {
	    errorPage("User already exists", data);
	  }
	}
  });
  window.onload = resize;
  window.onresize = resize;
}

function initNavbar() {
  $('#lucky-nav-group .btn').on('click', function(event) {
    current_view = $(this).find('input').val();
	showView(current_view);
  });
  $('#lucky-hat-btn').on('click', function(event) {
	var val = $(this).hasClass('active');
	include_hats=!val;
	if (current_view=="HOME") {
		showHome();
	} else if (current_view=="USER") {
		showUser();
	}
  });
}

function showView(view) {
	if (view==="HOME") {
		$("#lucky-pokemon-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-leader-page").hide();
		$("#lucky-friends-page").hide();
		$("#lucky-home-page").show();
		showHome();
	} else if (view==="USER") {
		$("#lucky-home-page").hide();
		$("#lucky-pokemon-page").hide();
		$("#lucky-leader-page").hide();
		$("#lucky-friends-page").hide();
		$("#lucky-user-page").show();
		showUser();
	} else if (view==="POKEMON") {
		$("#lucky-home-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-leader-page").hide();
		$("#lucky-friends-page").hide();
		$("#lucky-pokemon-page").show();
		showPokemonPage();
	} else if (view==="LEADER") {
		$("#lucky-home-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-pokemon-page").hide();
		$("#lucky-friends-page").hide();
		$("#lucky-leader-page").show();
		showLeaderboard();
	} else if (view==="FRIENDS") {
		$("#lucky-home-page").hide();
		$("#lucky-user-page").hide();
		$("#lucky-pokemon-page").hide();
		$("#lucky-leader-page").hide();
		$("#lucky-friends-page").show();
		showFriendsPage();
	}
	resize();
}

function initHome() {
  // resize scrollable pokemon grid when user collapses/expands header
  $('.navbar-collapse').on('shown.bs.collapse', function () {
    resize();
  })
  $('.navbar-collapse').on('hidden.bs.collapse', function () {
    resize();
  });
  // setup the pokemon filter
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
      var postData = $.map(data, function (obj) {
    	var newObj = {
          id: obj.id,
          text: obj.displayName,
          selected: false,
          friends: obj.friends
    	}
        return newObj;
      });
      userSelect = $('#user-search').select2({
        placeholder: "Select a trainer",
        dropdownAutoWidth : true,
        width: 'auto',
        data: postData,
        templateResult: function(data) {
          var userClass = "badge-primary";
          if (data.friends!==undefined && data.friends==true) {
        	  userClass = "badge-success";
          }
          var element = $("<button class=\"badge " + userClass + " lucky-user-menu-item\">"+data.text+"</button>");
          return element;
        }
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

function showUser() {
  var data = $('#user-search').select2('data');
  if (data!==undefined && data.length!=0 && data[0].id!="") {
    showUserPokemon(data[0].id);
  }
}

function showUserPokemon(id) {
  $.get(api_url+"/pokemon/user/"+id, function(data) {
	var width = getImageSize("#user-pokemon");
    $("#user-pokemon").html("");
	var str = "";
	if (data.length!=0) {
	  for (var i=0; i<data.length; i++) {
		if (!include_hats && data[i].costume!==0) {
	      continue;
	    }
	    str += "<button id=\"user-pokemon-" + data[i].id + "\" class=\"lucky-cell\">";
	    str += getCellHtml(data[i], width);
	    str += "</button>\n";
	  }
    } else {
		str="<div class=\"jumbotron lucky-professor-jumbo\">" +
		  "<p class=\"lead\">This trainer is a Pokemon master...they have got them all!</p>" +
		  "<div class=\"lucky-professor\"></div></div>";
	} 
	$("#user-pokemon").html(str);
	searchUserPokemon();
	resize();
  });
}

function initPokemon() {
  $.ajax({
    type: "GET",
	contentType: "application/json; charset=utf-8",
	url: "/pokemon/all",
	success: function (data) {
	  $('#question-search').select2({
	    placeholder: "Select a Pokemon",
	    width: '90%',
	    data: data
	  });
	  $('#question-search').on("select2:select", function(e) {
	    $("#question-users").show();
	    showUsersForPokemon(e.params.data.id);
	  });
	},
	error: function (result) {
	  errorPage("Failed to query pokemon data", result);
	}
  });
}

function showPokemonPage() {
	var selected = $('#question-search').select2('data');
	if (selected.length!=0 && selected[0].id!="") { 
	  showUsersForPokemon(selected[0].id);
	}
}

function showUsersForPokemon(id) {
  $.get(api_url+"/user/pokemon/"+id, function(data) {
	$("#question-users").html("");
	var str = "";
	if (data.length!=0) {
	  for (var i=0; i<data.length; i++) {
		var userClass = "badge-primary";
		if (data[i].friends==true) {
			userClass = "badge-success";
		}
	    str += "<span id=\"user-" + data[i].id + "\" class=\"badge " + userClass + " lucky-user-cell\">";
	    str += data[i].displayName;
	    str += "</span>\n";
	  }
	} else {
		str="<div class=\"jumbotron lucky-professor-jumbo\">" +
		  "<p class=\"lead\">Nobody needs this Pokemon...well maybe one person does</p>" +
		  "<div class=\"lucky-professor\"></div></div>"
	}
	$("#question-users").html(str);
	resize();
  });
}

function showHome() {
  $.get(api_url+"/pokemon", function(data) {
	var width = getImageSize("#pokemon");
    $("#pokemon").html("");
    var str = "";
    for (var i=0; i<data.length; i++) {
      if (!include_hats && data[i].costume!==0) {
    	  continue;
      }
      str += "<button id=\"pokemon-" + data[i].id + "\" class=\"lucky-cell\" ";
      if (data[i].available===true) {
        str+= "onclick=\"selectPokemon(" + data[i].id + ")\"";
      }
      str += ">";
      str += getCellHtml(data[i], width);
      str += "</button>\n";
    }
    $("#pokemon").html(str);
    searchPokemon();
    resetPercentage();
    resize();
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
	url: api_url+"/pokemon/"+id,
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
  var imgClass = "lucky-img ";
  if (data.done===true) {
	labelClass = "lucky-got";
	str += "<img class=\"lucky-cell-tick\" src=\"images/tickmark.png\"></img>";
  }
  var image_url = getImageUrl(data);
  str += "<img src=\"" + image_url + "\" ";
  str += "width=\"" + width + "\" ";
  if (data.done!==true) {
	  imgClass += "lucky-img-outline";
  }
  str += " class=\""+imgClass+"\"></img>";
  if (data.available!==true) {
	  str += "<span class=\"lucky-img-notavailable\"></span>";
  }
  str += "<span class=\"lucky-cell-label " + labelClass + "\">" + data.name + "</span>" +
	"</button>\n";
  return str;
}

function padNumber3(val) {
  var res = "";
  if (val<10) {
    res = "00" + val;
  } else if (val<100) {
	res = "0" + val;
  } else {
	res = val;
  }	
  return res;
}

function padNumber2(val) {
  var res = "";
  if (val<10) {
    res = "0" + val;
  } else {
	res = val;
  }	
  return res;
}
	
function getImageUrl(data) {
  var iconId = padNumber3(data.dexid);
  var regionId = padNumber2(data.region);
  var costumeId = padNumber2(data.costume);
  var image_url = "";
  if (page_mode=="shiny") {
    image_url = "images/shiny/";
  } else {
	image_url = "images/";
  }
  image_url += "pokemon_icon_" + iconId + "_" + regionId;
  if (costumeId!="00") {
	  image_url += "_" + costumeId;
  }
  if (page_mode=="shiny") {
	  image_url += "_shiny";
  }
  if (data.done!==true) {
	  image_url += "_d";
  }
  image_url += ".png";
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
    showView("HOME");
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
  filterText = input.value.toUpperCase().trim();
  grid = document.getElementById("pokemon");
  cells = grid.getElementsByClassName("lucky-cell");
  
  // Loop through all grid rows, and hide those who don't match the search query
  var need = 0;
  for (i = 0; i < cells.length; i++) {
    label = cells[i].getElementsByClassName("lucky-cell-label")[0];
	if (!label) {
		continue;
	}
	txtValue = label.textContent || label.innerText;
	if (label.classList.contains("lucky-need")) {
		need++;
	}
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
  if (filter=="NEED" && need==0) {
    var str="<div class=\"jumbotron lucky-professor-jumbo\">";
    if (page_mode=="lucky") {
	  str += "<p class=\"lead\">You are a Pokemon master! You have got all the lucky Pokemon.</p>";
    } else {
      str += "<p class=\"lead\">You are a Pokemon master! You have got all the shiny Pokemon.</p>";	
    }
	str += "<div class=\"lucky-professor\"></div></div>";
    $('#got-them-all').html(str);
    $('#got-them-all').css("display","");
  } else {
	$('#got-them-all').html("");
	$('#got-them-all').css("display","none");
  }
}

function resetPercentage() {
  $.get(api_url+"/user/stats", function(data) {
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
    url: api_url+"/user/leaderboard",
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
          	"scrollY": "400px",
          	"scrollX": true,
          	"searching": false,
          	"lengthChange": false,
          	"paging": false
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

function resize() {
  var headerDiv = document.getElementById("lucky-nav-outer");
  var headerHeight = headerDiv.offsetHeight + 20; // for bit at bottom of iphone screen

  // resize home page
  var gridHeader = document.getElementById("lucky-grid-header");
  var gridHeaderHeight = gridHeader.offsetHeight;
  setContentHeight("grid-container", headerHeight+gridHeaderHeight);
  
  // resize user page
  var helpHeader = document.getElementById("lucky-user-help");
  var userHeaderHeight = headerHeight + helpHeader.offsetHeight;
  var searchHeader = document.getElementById("lucky-user-search");
  userHeaderHeight += searchHeader.offsetHeight;
  var pokemonHeader = document.getElementById("user-pokemon-header");
  userHeaderHeight += pokemonHeader.offsetHeight;
  setContentHeight("user-pokemon", userHeaderHeight);
  
  // resize pokemon page
  helpHeader = document.getElementById("lucky-question-help");
  var pokemonHeaderHeight = headerHeight + helpHeader.offsetHeight;
  searchHeader = document.getElementById("lucky-question-search");
  pokemonHeaderHeight += searchHeader.offsetHeight;
  setContentHeight("question-users", pokemonHeaderHeight);

  // resize friends page
  var friendsHeader = document.getElementById("lucky-friends-help");
  var friendsHeaderHeight = headerHeight + friendsHeader.offsetHeight;
  setContentHeight("lucky-friends", friendsHeaderHeight);
}

function setContentHeight(id, height) {
  var viewportHeight = document.getElementsByTagName('body')[0].clientHeight;
  var element = document.getElementById(id);
  element.setAttribute("style","height:"+(viewportHeight - height)+"px");
  element.style.height=viewportHeight - height;
}

function friends() {
  $("#lucky-nav-group .btn").removeClass("active");
  showView("FRIENDS");
}

function showFriendsPage() {
  $.get("/user/friends", function(data) {
    $("#lucky-friends").html("");
	var str = "";
	for (var i=0; i<data.length; i++) {
	  str += getUserHtml(data[i].id, data[i].displayName, data[i].friends);
	}
	$("#lucky-friends").html(str);
	resize();
  });
}

function getUserHtml(id, name, friends) {
  var userClass = "badge-primary";
  if (friends) {
    userClass = "badge-success";
  }
  var str = "<button id=\"friend-" + id + "\" class=\"badge " + userClass + " lucky-user-cell\"" +
    " onclick=\"selectUser(" + id + ")\">";
  str += name;
  if (friends) {
    str += "<i class=\"fa fa-check lucky-user-icon\"></i>";
  } else {
	str += "<i class=\"fa fa-times lucky-user-icon\"></i>";
  }
  str += "</button>\n";
  return str;
}

function selectUser(id) {
  // find out if already selected  
  var element = "#friend-" + id;
  var selected = $(element).hasClass("badge-success");
  var data = {
    friends: !selected
  }
  // update it
  $.ajax({
	type: "POST",
	contentType: "application/json; charset=utf-8",
	url: "/user/friend/"+id,
	data: JSON.stringify(data),
	success: function (data) {
	  // Update the UI
	  $(element+" i").remove();
	  if (data.friends===true) {
	    $(element).removeClass("badge-primary");
	    $(element).addClass("badge-success");
	    $(element).append("<i class=\"fa fa-check lucky-user-icon\"></i>");
	  } else {
		$(element).removeClass("badge-success");
		$(element).addClass("badge-primary");
		$(element).append("<i class=\"fa fa-times lucky-user-icon\"></i>");
	  }
	  // Update the user dropdown on the other page
	  if (userSelect!=null) {
		  userSelect.val(null).trigger('change');
		  userSelect.select2('destroy');
		  userSelect.off('select2:select');
		  userSelect.html('<option></option>');
		  $("#user-pokemon").html("");
		  $("#lucky-user-searchbox").val("");
	  }
	  initUser();
	},
	error: function (result) {
	  errorPage("Failed to update friendship", result);
	}
  });
}