let ids = ['id','firstName','lastName','salary','jobId'];
let show = function(data){
	let inputs = $('#detail>input'); 
	let job = $('#job');
	inputs.push(job);
	for(let i =0; i<inputs.length; i++){
		$(inputs[i]).val(data[ids[i]]);
	}
	
	$('#changeBtn').on('click',modify);
}

function modify(){
	let inputs = $('#detail>input');
	let job = $('#job');
	inputs.push(job);
	
	let para = {};
	for(let i =0; i<inputs.length; i++){
		para[ids[i]]=$(inputs[i]).val()
	}
	$.ajax({
		url:'PutEmpInfoServlet',
		data: para
	})
}