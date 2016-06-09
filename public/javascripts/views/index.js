/**
 * Created by Jonathan on 31/05/2016.
 */
/*window.setTimeout("location=('localhost:9800/login');",5000);
window.location.href = "localhost:9800/login",5000;*/
$(function()
{

    $.post("/Application/getCurrentUser",function(ret)
    {
        if (ret.isError)
        {
            window.location='/login';
        }
        else
        {
            window.location='/menu';
        }

    })

});