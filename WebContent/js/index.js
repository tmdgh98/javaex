

let showList = function(result, b, c, d){
				console.log(result);
				console.log(b);
				console.log(c);
				console.log(d);
				let data = result;
				console.log(data);
				let ul = $('<ul />');
				data.forEach((item, idx)=>{
					let li = $('<li />').html(item.id + ", ").attr('id', item.id);
					//li.mouseover(function(){})
					$(li).on({
						'mouseover': mouseOverCallback,
						'mouseout':function(){
							$(this).css('background','');
						}
					});
					let aTag = $('<a />').html(item.firstName).attr('href','jquery_empInfo.jsp?id='+item.id);
					li.append(aTag);
					ul.append(li);
					
					localStorage.setItem('eid'+item.id, item.id);
					localStorage.setItem('fNm'+item.id, item.firstName);
					localStorage.setItem('lNm'+item.id, item.lastName);
					localStorage.setItem('eml'+item.id, item.email);
					localStorage.setItem('sal'+item.id, item.salary);
					localStorage.setItem('jid'+item.id, item.jobId);
				});
				$('#result').append(ul);
			}

let mouseOverFunc = function(){
						$(this).css('background','yellow');
						let id = $(this).attr('id');
						
						$.ajax({
							url:'GetEmpInfoServlet',
							data:{id : id},
							success : mof,
							dataType : 'json'
						})
						
					}
					
function mof(result) {
						console.log(result)
						console.log(result.currentTarget)
						let inputs = ['id','firstName','lastName','email','salary','jobId'];
						let input = $('#detail>input');
						for(let i =0; i<inputs.length;i++){
							//input[i].value=result[inputs[i]];
						}
					 }
function mouseOverCallback(event){
	console.log(event);
	
	let eid = localStorage.getItem('eid'+event.currentTarget.id);
	let fNm = localStorage.getItem('fNm'+event.currentTarget.id);
	let lNm = localStorage.getItem('lNm'+event.currentTarget.id);
	let eml = localStorage.getItem('eml'+event.currentTarget.id);
	let sal = localStorage.getItem('sal'+event.currentTarget.id);
	let jid = localStorage.getItem('jid'+event.currentTarget.id);
	
	$('#id').val(eid);
	$('#fName').val(fNm);
	$('#lName').val(lNm);
	$('#email').val(eml);
	$('#salary').val(sal);
	$('#jobId').val(jid);
}