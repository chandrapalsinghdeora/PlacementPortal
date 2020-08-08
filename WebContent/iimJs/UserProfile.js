/**
 * author :Komal
 */

/*
 * function removeValues(){ alert("inside removed values funstion::"+summerId);
 * alert("internshipIds::"+summerInternshipId); for(var i=0;i<summerId.length;i++){
 * if($('#'+summerInternshipId[i]).is(":checked")){
 * 
 * }else{ alert("inside else condition::") summerId.splice( i,summerId[i] );
 * alert("after splice") } } alert(summerId); }
 */

function setOtherInternshipValue(count) {
	if ($("#otherInternship" + count + "Lock").is(":checked")) {
		var idVal = $("#otherHiddenLock" + count).val();
		$("#otherHiddenLock" + count).val("1");
	} else {
		var idVal = $("#otherHiddenLock" + count).val();
		$("#otherHiddenLock" + count).val("0");

	}
}

function setOtherInternshipApprove(count) {
	if ($("#otherInternship" + count + "Approve").is(":checked")) {
		var idVal = $("#otherHiddenApprove" + count).val();
		$("#otherHiddenApprove" + count).val("2");
	} else {
		var idVal = $("#otherHiddenApprove" + count).val();
		$("#otherHiddenApprove" + count).val("0");

	}
}

function setWorkLockValues(count) {
	if ($("#workExperience" + count + "Lock").is(":checked")) {
		var idVal = $("#whLock" + count).val();
		$("#whLock" + count).val("1");
	} else {
		var idVal = $("#whLock" + count).val();
		$("#whLock" + count).val("0");

	}
}

function setWorkApproveValue(count) {
	if ($("#workExperience" + count + "Approve").is(":checked")) {
		var idVal = $("#whApprove" + count).val();
		$("#whApprove" + count).val("2");
	} else {
		var idVal = $("#whApprove" + count).val();
		$("#whApprove" + count).val("0");

	}
}

function setcvApprove(count) {
	if ($("#cv" + count + "Approve").is(":checked")) {
		var idVal = $("#cvApproveHidden" + count).val();
		$("#cvApproveHidden" + count).val("2");
	} else {
		var idVal = $("#cvApproveHidden" + count).val();
		$("#cvApproveHidden" + count).val("0");

	}
}

function setcvLock(count) {
	if ($("#cv" + count + "Lock").is(":checked")) {
		var idVal = $("#cvLockHidden" + count).val();
		$("#cvLockHidden" + count).val("1");
	} else {
		var idVal = $("#cvLockHidden" + count).val();
		$("#cvLockHidden" + count).val("0");

	}
}

function checkNumber(id, msg) {
	$("#message").fadeIn(1000);
	var str = document.getElementById(id).value;
	var re = new RegExp("[0-9]+");
	if (re.test(str)) {
		document.getElementById("message").innerHTML = "";
	} else {
		// alert("inside else condition::");
		// document.getElementById("firstNext").addClass('');
		document.getElementById(id).value = "";
		document.getElementById(id).focus();
		document.getElementById("message").innerHTML = msg;
		$("#message").fadeOut(8000);
	}
}

function validEmail(id, msg) {
	$("#message").fadeIn(1000);
	var str = document.getElementById(id).value;
	var re = new RegExp("[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}");
	if (re.test(str)) {
		document.getElementById("message").innerHTML = "";
	} else {
		document.getElementById(id).value = "";
		document.getElementById(id).focus();
		document.getElementById("message").innerHTML = msg;
		$("#message").fadeOut(6000);
	}
}

function deleteSummerInternship(pkId, id) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == 'succcess') {
				document.getElementById(id).style.display = 'none';
			} else {

			}
		}
	};
	xhttp.open("POST", "deleteSummerInternShip?pkId=" + pkId, true);
	xhttp.send();
}

function deleteOtherInternship(pkId, divId) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == 'success') {
				document.getElementById(divId).style.display = 'none';
			} else {

			}
		}
	};
	xhttp.open("POST", "otherInternshipDelete?pkId=" + pkId, true);
	xhttp.send();
}

function deleteWorkExperience(pkId, divId,durationId) {
	var durationValue=document.getElementById(durationId).value;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == 'success') {
				var totalExp1 =parseInt($("#totalWorkExp").val()) ;
				var total = totalExp1-parseInt(durationValue) ;
				$("#totalWorkExp").val(total);
				document.getElementById(divId).style.display = 'none';
			} else {

			}
		}
	};
	xhttp.open("POST", "workExperienceDelete?pkId=" + pkId, true);
	xhttp.send();
}

function deleteCv(pkId, divId) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == 'success') {
				document.getElementById(divId).style.display = 'none';
			} else {

			}
		}
	};
	xhttp.open("POST", "cvDelete?pkId=" + pkId, true);
	xhttp.send();
}

function checkFileType(id) {
    var fileName=document.getElementById(id).value;
    var ext = $('#'+id).val().split('.').pop().toLowerCase();
    if($.inArray(ext, ['pdf']) == -1) {
            alert("Only PDF file is allowed to upload.")
            document.getElementById(id).value="";
    }else{
          
    }
}

function checkFileTypeForPhoto(id){
	 var fileName=document.getElementById(id).value;
	    var ext = $('#'+id).val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['jpeg','jpg','png']) == -1) {
	            alert("Only jpeg, jpg, png File are allowed to upload.")
	            document.getElementById(id).value="";
	    }else{
	            
	    }
}

function checkNullValue(id){
	var value=document.getElementById(id).value;
	if(value==""){
		//document.getElementById("message").value="Please enter value";
		document.getElementById(id).style.borderColor = "red";
		document.getElementById(id).focus();
	}else{
		document.getElementById(id).style.borderColor = "black";
	}
}

function setRequiredFieldForOther(company,duration,location){
	//alert("inside function::");
	if(document.getElementById(company).value =="" && document.getElementById(duration).value =="" && document.getElementById(location).value==""){
	   document.getElementById(company).required = false;
		  document.getElementById(duration).required = false;
		  document.getElementById(location).required = false;
	}else if(document.getElementById(company).value !="" || document.getElementById(duration).value !="" || document.getElementById(location).value!="")
	  {
		  document.getElementById(company).required = true;
		  document.getElementById(duration).required = true;
		  document.getElementById(location).required = true;
	  }else{
		  document.getElementById(company).required = false;
		  document.getElementById(duration).required = false;
		  document.getElementById(location).required = false;
	  }
}

function requiredFunction(compName, designation, duration, industry,
		functionalArea) {
	if (document.getElementById(compName).value == ""
			&& document.getElementById(designation).value == ""
			&& document.getElementById(duration).value == ""
			&& document.getElementById(industry).value == ""
			&& document.getElementById(functionalArea).value == "") {
		  document.getElementById(compName).required = false;
		  document.getElementById(designation).required = false;
		  document.getElementById(duration).required = false;
		  document.getElementById(industry).required = false;
		  document.getElementById(functionalArea).required = false;
	}else if (document.getElementById(compName).value != ""
			|| document.getElementById(designation).value != ""
			|| document.getElementById(duration).value != ""
			|| document.getElementById(industry).value != ""
			|| document.getElementById(functionalArea).value != ""){
		 document.getElementById(compName).required = true;
		  document.getElementById(designation).required = true;
		  document.getElementById(duration).required = true;
		  document.getElementById(industry).required = true;
		  document.getElementById(functionalArea).required = true;
	}else{
		 document.getElementById(compName).required = false;
		  document.getElementById(designation).required = false;
		  document.getElementById(duration).required = false;
		  document.getElementById(industry).required = false;
		  document.getElementById(functionalArea).required = false;
	}
}

/*function addDuration(id){
	alert("inside function::");
	var ids=document.getElementsByName("durationWorkExperience");
	alert("ids::::"+ids)
	var totalExp=parseInt($("#totalWorkExp").val());
	var totalExp1 =parseInt($("#"+id).val()) ;
	var total = totalExp1+totalExp ;
	$("#totalWorkExp").val(total);

}*/

function changeDuration(id,value){
	var totalExp=parseInt($("#totalWorkExp").val());
	var totalExp1 =parseInt($("#"+id).val()) ;
	var total = totalExp-value ;
	total=total+totalExp1;
	$("#totalWorkExp").val(total);
}



