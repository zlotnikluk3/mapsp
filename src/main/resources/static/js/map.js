var mark;
var longpress = false;
var map;
var infowindow;
var lat;
var lng;
function initAutocomplete() {
	map = new google.maps.Map(document.getElementById("map"), {
		center: { lat: 53.135278, lng: 23.145556 },
		zoom: 13,
		mapTypeId: "roadmap",
	});
	// Create the search box and link it to the UI element.
	const input = document.getElementById("pac-input");
	const searchBox = new google.maps.places.SearchBox(input);
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
	// Bias the SearchBox results towards current map's viewport.
	map.addListener("bounds_changed", () => {
		searchBox.setBounds(map.getBounds());
	});
	// Listen for the event fired when the user selects a prediction and retrieve
	// more details for that place.
	map.addListener("click", (e) => {
		if (longpress) { placeMarkerAndPanTo(e.latLng, map); }
	});

	map.addListener("mousedown", (e) => {
		start = new Date().getTime();
	});

	map.addListener("mouseup", (e) => {
		end = new Date().getTime();
		longpress = (end - start < 500) ? false : true;
	});

	searchBox.addListener("places_changed", () => {
		const places = searchBox.getPlaces();

		if (places.length == 0) {
			return;
		}

		// For each place, get the icon, name and location.
		const bounds = new google.maps.LatLngBounds();
		places.forEach((place) => {
			if (!place.geometry) {
				console.log("Returned place contains no geometry");
				return;
			}

			placeMarkerAndPanTo(place.geometry.location, map);

			if (place.geometry.viewport) {
				// Only geocodes have viewport.
				bounds.union(place.geometry.viewport);
			} else {
				bounds.extend(place.geometry.location);
			}
		});
		map.fitBounds(bounds);
	});
}

function placeMarkerAndPanTo(latLng, map) {
	if (mark) {
		mark.setPosition(latLng);
		infowindow.close();
		infowindow = new google.maps.InfoWindow({
			content: 'Latitude: ' + latLng.lat() + '<br>Longitude: ' + latLng.lng()
		});
		lat = latLng.lat()
		lng = latLng.lng()
	} else {
		mark = new google.maps.Marker({
			position: latLng,
			map: map
		});
		infowindow = new google.maps.InfoWindow({
			content: 'Latitude: ' + latLng.lat() + '<br>Longitude: ' + latLng.lng()
		});
		lat = latLng.lat()
		lng = latLng.lng()
	}
	infowindow.open(map, mark);
	map.panTo(latLng);
}

$(document).ready(function() {
	$('#bedi').attr("disabled", true);
	$('#success').hide();
	$("#bsv").click(function(event) {
		event.preventDefault();
		$('#latfield').val(lat);
		$('#lngfield').val(lng);
		if (mark) {
			$.ajax({
				url: '/save',
				type: 'POST',
				data: {
					opis: $("#ta").val(),
					latt: lat,
					lngg: lng
				},
				success: function(data) {
					$.ajax({
						url: "/last",
						type: 'GET',
						success: function(value) {
							$("#task").append(new Option($("#ta").val(), value));
							$("#task").val(value);
							$('#bedi').attr("disabled", false);
							$('#result').val("Wysłano");
							$('#suctxt').text("Wysłano");
							$('#success').show();
						}
					});
				}
			})
		}
		else { alert("Musisz wybrać lokalizację!"); }
	});

	$("#bedi").click(function(event) {
		event.preventDefault();
		$('#latfield').val(lat);
		$('#lngfield').val(lng);

		$.ajax({
			url: "/edit/" + $('#task').val() + "/",
			type: 'POST',
			data: {
				opis: $("#ta").val(),
				latt: lat,
				lngg: lng
			},
			success: function(data) {
				var i = $("select#task").val()
				$('#task option[value=' + i + ']').text($("#ta").val());
				$('#result').val("Zedytowano");
				$('#suctxt').text("Zedytowano");
				$('#success').show();
			}
		})
	});

	$("#delbtn").click(function(event) {
		if ($("#task").val() != 0) {
			if (confirm("Czy na pewno chcesz usunąć?")) {
				$.ajax({
					url: "/delfromlist/" + $('#task').val() + "/",
					type: 'POST',
					data: {
						opis: $("#ta").val(),
						latt: lat,
						lngg: lng
					},
					success: function(data) {
						$("#task option:selected").remove();
						infowindow.close();
						placeMarkerAndPanTo(null, map);
					}
				})
			}
			else {
				return false;
			}
		}
	});

	$('select').on('change', function(event) {
		var optionSelected = $("option:selected", this);
		var txt = optionSelected.text();
		var position;
		if ($("#task").val() == 0) {
			$('#bedi').attr("disabled", true);
			$("#ta").val("")
			infowindow.close();
			placeMarkerAndPanTo(null, map);
		} else {
			$('#bedi').attr("disabled", false);
			$.ajax({
				url: "/coor/" + $('#task').val() + "/",
				type: 'GET',
				success: function(value, data) {
					lat = value.lat;
					lng = value.lng
					if (infowindow) { infowindow.close(); }
					infowindow = new google.maps.InfoWindow({
						content: 'Latitude: ' + lat + '<br>Longitude: ' + lng
					});
					position = new google.maps.LatLng(lat, lng);
					$("#ta").val(txt);
					placeMarkerAndPanTo(position, map);
				}
			});
		}
	});
});

