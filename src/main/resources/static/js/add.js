
$(function () {
    var title = $("#title");
    var mdContent = $("#mdContent");
    var summary = $("#summary");
    var state = $("#state");

    $("#sb").click(function () {

        if (title.val() === null || title.val() === "") {
            alert("請填寫標題");
            return;
        }
        if (mdContent.val() === null || mdContent.val() === "") {
            alert("請填寫內容");
            return;
        }
        if (summary.val() === null || summary.val() === "") {
            alert("請填寫概述");
            return;
        }
        $("form").submit();
    });

    $("#back").click(function () {
        history.back(-1);
    })
});