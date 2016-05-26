function waitOn()
{
    $("#waitGlobal").css("width",$(document).width());
    $("#waitGlobal").css("height",$(document).height());
    $("#waitGlobalImg").css("left",($(document).width() - 287)/2);
    $("#waitGlobalImg").css("top",($(document).height() - 141)/2);
    $("#waitGlobal").show();
}

function waitOff()
{
    $("#waitGlobal").hide();
}