/**
 * Created by Administrator on 2017/10/30.
 */

    //�˵�
$(".menu-icon").click(function(){
    if($(".nav").css("display")=="none"){
        $(".nav").slideDown(150)
    }else{
        $(".nav").slideUp(150)
    }
})