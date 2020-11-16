function upload_check() {
	var fi = document.getElementById("file");
	if (fi.files.length > 0) {
		for (var i = 0; i <= fi.files.length - 1; i++) {
			const fsize = fi.files.item(i).size;
			if (fsize >= 10971520) {
				alert("El archivo seleccionado supera los 10 Mb y no puede ser procesado");
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

function myFunctionDisplay() {

	var x = document.getElementById("myDIV");

	if (document.getElementById("flow").value==="V") {
		if (x.style.display === "none") {
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	} else {
		x.style.display = "none";
	}
}

function controlarDisplay() {
	var elValorDiv = document.getElementById("flow").value;
	var elDivObjeto = document.getElementById(elValorDiv);
	var todosDiv = document.getElementById("flow").options;
	var i;
	for (i = 0; i < todosDiv.length; i++) {
		var losOtrosDivObjeto = document.getElementById(todosDiv[i].value);
		if (losOtrosDivObjeto.style.display === "block"
				&& losOtrosDivObjeto.value != elValorDiv) {

			losOtrosDivObjeto.style.display = "none";

		}
	}

	if (elDivObjeto.style.display === "none") {
		elDivObjeto.style.display = "block";
	} else {
		elDivObjeto.style.display = "none";
	}

}
