$(function () {
    var search = $("#search-btn");
    search.click(function () {
        var name = $("#title");
        if (name.val() === null || name.val() === '') {
            alert("請輸入");
            return;
        }
        console.log(search);
        search.submit();
    })
    $("a[id^='article_title_']").each(function () {
        $(this).on("click", function(){
            var id = $(this).attr("id").substr(14);
            sendAjax(id,0);
        });
    });
    $("a[id^='article_edit_']").each(function () {
        $(this).on("click", function(){
            var id = $(this).attr("id").substr(13);
            sendAjax(id,1);
        });
    });
    $("a[id^='article_delete_']").each(function () {
        $(this).on("click", function(){
            var id = $(this).attr("id").substr(15);
            sendAjax(id,2);
        });
    });
});

function sendAjax(id,choose){
    var ajax_type;
    if(choose == 0){
        ajax_type = 'GET';
    }else if(choose == 1){
        ajax_type = 'GET';
    }else if(choose == 2){
        ajax_type = 'DELETE';
    }
    $.ajax({
        type:ajax_type,
        url:'/article/' + id,
        data:{},
        cache:false,
        dataType:'json',
        success:function(data,textStatus, xhr){
            console.log(data);
            console.log(textStatus);
            console.log(xhr);
            if(choose == 2){
                location.reload();
            }else {
                var body = $(document.body),
                    form = $("<form method='post'></form>"),
                    input;
                if (choose == 0) {
                    form.attr({"action": "/getArticle"});
                } else if (choose == 1) {
                    form.attr({"action": "/findArticle"});
                }
                $.each(data, function (key, value) {
                    input = $("<input type='hidden'>");
                    input.attr({"name": key});
                    input.val(value);
                    form.append(input);
                });

                form.appendTo(document.body);
                form.submit();
            }
        },
        error:function(){}
    })
}