function upload_check()
{
	var fi = document.getElementById("file");	
	if (fi.files.length > 0) { 
        for (var i = 0; i <= fi.files.length - 1; i++) { 
            const fsize = fi.files.item(i).size; 
            if (fsize >= 10971520) { 
                alert( 
                  "El archivo seleccionado supera los 10 Mb y no puede ser procesado");
                document.getElementById("file").value = "";
                return false;
            } 
    }
     
	}
}


function myFunction() {
	  var x = document.getElementById("myDIV");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	  } else {
	    x.style.display = "none";
	  }
	}