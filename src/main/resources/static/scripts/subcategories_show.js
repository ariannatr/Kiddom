
function changeSelect( selectNo )
{
    var sels = document.getElementById("type2").getElementsByTagName('SELECT');
    for( var j=0; j<sels.length; j++ )
    {
        sels[j].style.display = "none";
        if ( j===(selectNo-1) )
        { sels[j].style.display = ''; }
    }
}
