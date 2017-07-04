
function changeSelect( selectNo )
{
    var cats=document.getElementById("type1");
    var selectMe=0;
    for( var i=0; i<cats.length; i++ )
    {
        if ( cats[i].value==selectNo )
        { selectMe=i; }
        cats[i].value = i+1;


    }
    var sels = document.getElementById("type2").getElementsByTagName('SELECT');
    for( var j=0; j<sels.length; j++ )
    {
        sels[j].style.display = "none";

        if ( j==(selectMe-1) )
        { sels[j].style.display = ''; }
    }
}

