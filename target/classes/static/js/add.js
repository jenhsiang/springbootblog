
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
        var formData = {
            'title' 		 : $('input[name=title]').val(),
            'summary' 		 : $('input[name=summary]').val(),
            'mdContent' 	 : $('input[name=mdContent]').val(),
            'state'          : $('select[name=state]').val()
        };
        $.ajax({
            type:'post',
            url:"/article",
            data:formData,
            cache:false,
            dataType:'json',
            success:function(data,textStatus, xhr){
                console.log(data);
                console.log(textStatus);
                console.log(xhr);
                if(data == 1){
                    location.href='/';
                }
            },
            error:function(){}
        });
    });

    $("#back").click(function () {
        history.back(-1);
    })
});