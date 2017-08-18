$().ready(function () {

    //side nav ctrl
    $(".nav-ctrl-btn").click(function () {
        $("#wrap").toggleClass("min-side");
    });

    //group tree open/close
    $(".tree a.icon").click(function () {
        $(this).parent().next().toggle();
        if($(this).find("i.iconfont").hasClass("icon-tree-close")){
            $(this).find("i.iconfont").attr("class", "iconfont icon-tree-open");
        }else{
            $(this).find("i.iconfont").attr("class", "iconfont icon-tree-close");
        }
    });

    //do tip postion set
    $(".do a span").each(function () {
        var objWidth = $(this).outerWidth();
        $(this).css("margin-left", -objWidth/2);
    });

    //pop box postion set
    $(".pop").each(function () {
        var objWidth = $(this).outerWidth();
        var objHeight = $(this).outerHeight();
        $(this).css("margin-left", -objWidth/2).css("margin-top", -objHeight/2-20);
    });

    //video page text show/hide
    $("#textBtn").click(function(){
        if($("#flexBox").hasClass("no-video")){
            if($(this).hasClass("active")){
                $("#scrollText").slideUp();
            }else{
                $("#scrollText").slideDown();
            }
        }else{
            if($(this).hasClass("active")){
                $("#videoBox").animate({marginRight:"0px"});
            }else{
                $("#videoBox").animate({marginRight:"240px"});
            }
        }
        $(this).toggleClass("active");
    });

    //video page postion fixed
    $(window).scroll(function(){
        var boxHeight = $("#flexBox").height();
        var scrollTop = $(window).scrollTop();
        if(scrollTop>=boxHeight+15){
            $(".play-page-top").addClass("p-fix");
        }else{
            $(".play-page-top").removeClass("p-fix");
        }
    });

    var inputObj = $("#sosoInput");
    var soAutoDropObj = $("#soso-auto");
    var advancedDropObj = $("#advancedDrop");
    var placeholder = inputObj.attr("placeholder");
    //高级搜索展开
    $("#advancedDropBtn").click(function(){
        advancedDropObj.toggle();
        soAutoDropObj.hide();
    });
    //搜索关键字
    inputObj.focus(function(){
        soAutoDropObj.show();
        advancedDropObj.hide();
    });
    inputObj.blur(function(){
        inputObj.attr("placeholder", placeholder);
    });
    soAutoDropObj.find("li").click(function(){
        var val = $(this).html();
        soAutoDropObj.hide();
        inputObj.attr("placeholder", "").val(val);
    });
    soAutoDropObj.find("li").mouseenter(function(){
        $(this).addClass("active");
    });
    soAutoDropObj.find("li").mouseleave(function(){
        $(this).removeClass("active");
    });
    $(document).keydown(function(e){
        if(soAutoDropObj.is(":visible")){
            if(e.keyCode==13){
                var val = soAutoDropObj.find("li.active").html();
                inputObj.attr("placeholder", "").val(val);
            }
            if(e.keyCode==38){
                if(soAutoDropObj.find("li").hasClass("active")){
                    soAutoDropObj.find("li.active").removeClass("active").prev().addClass("active");
                }else{
                    soAutoDropObj.find("li:last").addClass("active");
                }
            }
            if(e.keyCode==40){

                if(soAutoDropObj.find("li").hasClass("active")){

                    soAutoDropObj.find("li.active").removeClass("active").next().addClass("active");
                }else{
                    soAutoDropObj.find("li:first").addClass("active");
                }
            }
        }
    });
    $(document).bind('click', function(e) {
        var $clicked = $(e.target);
        if(!$clicked.is(".soso-bar *")){
            soAutoDropObj.hide();
            advancedDropObj.hide();
        }
    });






});



