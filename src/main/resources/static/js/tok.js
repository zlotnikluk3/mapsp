$(function() {
	$('#success').hide();
	$('.txt-copy').on('input', function(e) {
		$(this).closest('tr').find('.btn-copy').attr("at3", $(this).val());
	});

	$('.chbx-copy').change(function() {
		if ($(this).is(':checked')) { $(this).closest('tr').find('.btn-copy').attr("at2", "true"); }
		else { $(this).closest('tr').find('.btn-copy').attr("at2", "false"); }
	})

	$(".btn-copy").click(function() {
		var value1 = $(this).attr('at1');
		var value2 = $(this).attr('at2');
		var value3 = $(this).attr('at3');

		$.post("/upTok/" + value1 + "/", { saved: value2, nam: value3 }, res => {
			$('#suctxt').text("Zedytowano");
			$('#success').show();
		});
	});

	$(".btn-del").click(function() {
		var vt = $(this).attr('att');
		if (confirm("Czy na pewno chcesz usunąć?")) {
			$.post("/deleteTok/" + vt + "/", { page: 1, key: "94381" }, res => {
				location.reload();
			});
		}
	});
});
