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
  $.get("/user", function(data) {
    $("#user").html(data.displayName);
    $(".unauthenticated").hide();
    $(".authenticated").show();
    showPokemon();
  });
}

function showPokemon() {
  $.get("/pokemon", function(data) {
    $("#pokemon").html("");
    var str = "";
    for (var i=0; i<data.length; i++) {
      var labelClass = "lucky-need";
      str += "<button class=\"lucky-cell\" onclick=\"selectPokemon(" + data[i].id + ")\">";
      if (data[i].done===true) {
    	  labelClass = "lucky-got";
    	  str += "<img class=\"lucky-cell-tick\" src=\"images/tickmark.png\"></img>";
      }    
      str += "<img src=\"" + data[i].url + "\"></img>" +
        "<span class=\"lucky-cell-label " + labelClass + "\">" + data[i].name + "<\span>" +
        "</button>\n";
    }
    $("#pokemon").html(str);
  });
}

function selectPokemon(id) {
	  $.ajax({
	    type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "pokemon/"+id,
		success: function (data) {
		  // Update the UI 
		  alert("bingo");
		},
		error: function (result) {
		  errorPage("Failed to update pokemon", result);
		}
	  });
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
  // Declare variables
  var input, filter, grid, cells, label, i, txtValue;
  input = document.getElementById("lucky-searchbox");
  filter = input.value.toUpperCase();
  grid = document.getElementById("pokemon");
  cells = grid.getElementsByClassName("lucky-cell");

  // Loop through all grid rows, and hide those who don't match the search query
  for (i = 0; i < cells.length; i++) {
    label = cells[i].getElementsByClassName("lucky-cell-label")[0];
	if (label) {
	  txtValue = label.textContent || label.innerText;
	    if (txtValue.toUpperCase().indexOf(filter) > -1) {
	      cells[i].style.display = "";
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