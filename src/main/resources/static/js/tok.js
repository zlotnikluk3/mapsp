$(document).ready(function() {
	$('#success').hide();
	$('.txt-copy').on('input', function(e) {
		$(this).closest('tr').find('.btn-copy').attr("at3", $(this).val());
	});

	$('.chbx-copy').change(function() {
		if ($(this).is(':checked')) { $(this).closest('tr').find('.btn-copy').attr("at2", "true"); }
		else { $(this).closest('tr').find('.btn-copy').attr("at2", "false"); }
	})

	$(document).on("click", ".btn-copy", function() {
		var value1 = $(this).attr('at1');
		var value2 = $(this).attr('at2');
		var value3 = $(this).attr('at3');

		$.ajax({
			url: "/upTok/" + value1 + "/",
			type: 'POST',
			data: {
				saved: value2,
				nam: value3
			},
			success: function(data) {
				$('#suctxt').text("Zedytowano");
				$('#success').show();
			}
		})
	});

	$(document).on("click", ".btn-del", function() {
		var vt = $(this).attr('att');
		if (confirm("Czy na pewno chcesz usunąć?")) {
			$.ajax({
				url: "/deleteTok/" + vt + "/",
				type: 'POST',
				success: function() {
					location.reload();
				}
			})
		}
	});
});