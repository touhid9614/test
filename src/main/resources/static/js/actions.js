function btnPay() {
	//button.preventDefault();
	var btn = document.getElementById('payNow');
	var conf = confirm("Do you want to proceed!");
	//alert(conf);
	if(conf == true)
	{
		btn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...';
		btn.disabled=true;
		return true;
	}
	else
	{
		var mytxt='<span>No Bill Found</span>';
		alert(mytxt);
	}
}
function getChkBtn(){
	var btn = document.getElementById('chkbtn');
	var conf = confirm("Do you want to proceed!");
	//alert(conf);
	if(conf == true){
		btn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...';
		btn.disabled=true;
		return true;
	}
	else
	{
		var mytxt='<span>No Bill Found</span>';
		alert(mytxt);
	}
	return false;

}
function showReport(){
	var btn = document.getElementById('showRptBtn');
	var conf = confirm("Do you want to proceed!");
	//alert(conf);
	if(conf == true){
		btn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...';
		btn.disabled=true;
		return true;
	}
	return false;

}
function myPaybuttonFunction() {
 document.getElementById("payNow").innerHTML="";

}
function ExportToExcel(type, fn, dl) {
	var elt = document.getElementById('tbl_exporttable_to_xls');
	var wb = XLSX.utils.table_to_book(elt, { raw:true, sheet: "sheet1" });
	var dt = new Date().toDateString();
	return dl ?
		XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' }) :
		XLSX.writeFile(wb, fn || ('Detailed_Report_'+ dt +"." + (type || 'xlsx')));
}

function showdateformate() {
	type="text/javascript">
		$(".date").datepicker({
			format: "dd-mm-yyyy",
		});
}

function fnExcelReport()
{
	var tab_text="<table border='2px'><tr bgcolor='#FFFF'>";
	var textRange; var j=0;
	var tab = document.getElementById('tbl_exporttable_to_xls'); // id of table

	for(j = 0 ; j < tab.rows.length ; j++)
	{
		tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
		//tab_text=tab_text+"</tr>";
	}

	tab_text=tab_text+"</table>";
	tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
	tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
	tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

	var ua = window.navigator.userAgent;
	var msie = ua.indexOf("MSIE ");

	if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
	{
		txtArea1.document.open("txt/html","replace");
		txtArea1.document.write(tab_text);
		txtArea1.document.close();
		txtArea1.focus();
		sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Submit.xls");
	}
	else                 //other browser not tested on IE 11
		var sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));

	return (sa);
}

function exportReportToExcel() {
	var table = document.getElementsByTagName("tbl_exporttable_to_xls"); // you can use document.getElementById('tableId') as well by providing id to the table tag
	 table.convert(table[0], { // html code may contain multiple tables so here we are refering to 1st table tag
		name: 'export.xls', // fileName you could use any name
		sheet: {
			name: 'Sheet 1' // sheetName
		}
	});
}
function tableToExcelX() {
	var dt = new Date().toDateString();
	var uri = 'data:application/vnd.ms-excel;base64,'
		, template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" ><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
		, base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
		, format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }

		var table = document.getElementById('tbl_exporttable_to_xls')
		var ctx = {worksheet: 'Day End Report' || 'Day End Report', table: table.innerHTML}
		window.location.href = uri + base64(format(template, ctx))

}




