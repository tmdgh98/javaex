// //product.js
$.ajax({
	url:'../GetProductServlet',
	dataType: 'json',
	success:function(result){
		console.log(result);
		for(obj of result){
			createRow(obj);
		}
	}
});
// <div class="col-lg-4 col-md-6 mb-4">
//             <div class="card h-100">
//               <a href="#">
					//<img class="card-img-top" src="http://placehold.it/700x400" alt="">
				//</a>
//               <div class="card-body">
//                 <h4 class="card-title">
//                   <a href="#">Item One</a>
//                 </h4>
//                 <h5>$24.99</h5>
//                 <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
//               </div>
//               <div class="card-footer">
//                 <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
//               </div>
//             </div>
//           </div>
function createRow(obj){
	let div1 = $('<div />').attr('class','col-lg-4 col-md-6 mb-4');
	let div2 = $('<div />').attr('class','card h-100');
	let div2_a = $('<a />').attr('href','#');
	let img = $('<img />').attr({'class':'card-img-top','src':"http://placehold.it/700x400"});
	div2_a.append(img);
	div2.append(div2_a);
	let div2_div = $('<div />').attr('class','card-body');
	let h4 = $('<h4 />').attr('class','card-title');
	let div_a = $('<a />').attr('href','#');
	div_a.html(obj.itemName);
	h4.append(div_a);
	div2_div.append(h4);
	let price = new Intl.NumberFormat('ko-KR',{style: 'currency', currency:'KRW'}).format(obj.price);
	let h5 = $('<h5 />').html(price);
	div2_div.append(h5);
	let p = $('<p />').attr("class","card-text");
	p.html(obj.itemDesc);
	div2_div.append(p);
	div2.append(div2_div);
	let div2_div2 = $('<div />').attr('class','card-footer');
	/*let small = $('<small />').attr('class','text-muted');
	let starNum = Math.floor(obj.likeIt);
	let star ="";
	let ic = $('<i />').attr('class', 'fas');
	for(let i=0;i<starNum;i++){
		star += '&#9733';
	}
	if(obj.likeIt-starNum>0){
		star += '&#9734'
	}
	small.html(star);*/
	let star = "";
   let like = Math.floor(obj.likeIt);
   
   let ic ="";
   for(let i=0;i<like;i++){
      ic += '<i style="font-size:24px" class="fas">&#xf164;</i>';
      star += '&#9733';
   }
   if(obj.likeIt>like){
      ic += '<i style ="font-size:24px" class="far">&#xf164;</i>'
      star+='&#xf164';
   }
   let small = $('<small />').attr('class','text-muted').html(ic);
	div2_div2.append(small);
	div2.append(div2_div2);
	div1.append(div2);
	$('div.col-lg-9>div.row').append(div1);
	console.log($('div.col-lg-9>div.row'));
}