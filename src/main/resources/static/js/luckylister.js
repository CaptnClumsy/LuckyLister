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
  $.ajax({
	type: "GET",
    contentType: "application/json; charset=utf-8",
	url: "/user",
	success: function (data) {
	  $("#user").html(data.displayName);
	  $(".unauthenticated").hide();
	  $(".authenticated").show();
	  showPokemon();
	}
  });
}

function showPokemon() {
  $('#lucky-filter-group .btn').on('click', function(event) {
    var val = $(this).find('input').val();
    filterPokemon(val);
  });
  $.get("/pokemon", function(data) {
    $("#pokemon").html("");
    var str = "";
    for (var i=0; i<data.length; i++) {
      var labelClass = "lucky-need";
      str += "<button id=\"pokemon-" + data[i].id + "\" class=\"lucky-cell\" onclick=\"selectPokemon(" + data[i].id + ")\">";
      str += getCellHtml(data[i]);
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

function getCellHtml(data) {
  var str = "";
  var labelClass = "lucky-need";
  var imgClass = "";
  if (data.done===true) {
	labelClass = "lucky-got";
	str += "<img class=\"lucky-cell-tick\" src=\"images/tickmark.png\"></img>";
  }    
  str += "<img src=\"" + data.url + "\" ";
  if (data.done!==true) {
	  str += " class=\"lucky-img-outline\"";
  }
  str += "></img>" +
    "<span class=\"lucky-cell-label " + labelClass + "\">" + data.name + "<\span>" +
	"</button>\n";
  return str;
}

function logout() {
  $.post("/logout", function() {
    $("#user").html('');
    $(".unauthenticated").show();
    $(".authenticated").hide();
  })
  return true;
}

function searchPokemon() {
  // Find which radio button is selected
  var filter = $('#lucky-filter-group input:radio:checked').val()
  filterPokemon(filter);
}

function filterPokemon(filter) {
  // Declare variables
  var input, filter, grid, cells, label, i, txtValue;
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