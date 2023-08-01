 $(document).ready(function () {
	$("#accordion li > h4").click(function () {

		if ($(this).next().is(':visible')) {
			$(this).next().slideUp(300);
			$(this).children(".plusminus").text('+');
		} else {
			$(this).next("#accordion ul").slideDown(300);
			$(this).children(".plusminus").text('-');
		}
	});

	  $(".btn-delete").on("click", function (e) {
          e.preventDefault();
          link = $(this);

          delTitle = link.attr("delTitle");
          $("#yesBtn").attr("href", link.attr("href"));
          $("#confirmText").html("Do you want to delete " +delTitle + " ?");
          $("#confirmModal").modal();
        });

         $("#btnCancel").on("click", function (e) {
              e.preventDefault();
              $("#keyword").text("");
              link = $(this);
               home = link.attr("home");
              window.location = "/"+home;
            });

});