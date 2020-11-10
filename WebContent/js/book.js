function addDiv() {
    let div, form, input, label;
    div = $('<div />').attr('id', 'addBook');
    form = $('<form />').attr('id', 'addForm');
    let ids = ['addTitle', 'addAuthor', 'addPrice'];
    let labels = ['제목 : ','작가 : ','가격 : '];
    for (let i =0; i<ids.length; i++) {
        label = $('<lable />').attr('for', ids[i]).html(labels[i]);
        input = $('<input />').attr('name', ids[i]);
        form.append(label ,input,$('<br />'));
    }
    form.append($('<input />').attr({
        type: 'button',
        value: '도서추가',
        onclick : 'addBook()'
    }))
    div.append(form);
    $('body').append(div);
}
$(gogo)

function gogo() {
    addDiv();
    modifyDiv();

    listBook();
}

function modifyDiv() {
    let div, form, input, label;
    div = $('<div />').attr('id', 'modifyBook').css('display','none');
    form = $('<form />').attr('id', 'modifyForm');
    let ids = ['modifyTitle', 'modifyAuthor', 'modifyPrice'];
    let labels = ['제목 : ','작가 : ','가격 : '];
    input = $('<input />').attr('name', 'modifyId').attr('type','hidden');
    form.append(input);
    for (let i =0; i<ids.length; i++) {
        label = $('<lable />').attr('for', ids[i]).html(labels[i]);
        input = $('<input />').attr('name', ids[i]);
        form.append(label ,input,$('<br />'));
    }
    form.append($('<input />').attr({
        type: 'button',
        value: '도서수정',
        onclick : 'modifyBook()'
    }))
	form.append($('<input />').attr({
        type: 'button',
        value: '취소',
        onclick : 'cancelModify()'
    }))
    div.append(form);
    $('body').append(div);
}

function listBook(){
    $.ajax({
        url : '../BookServlet',
        data : {
            cmd : 'bookAll'
        },
        dataType : 'json',
        success : function(data){
            
            let div,ul;
            div = $('<div />').attr('id', 'show');
            for(let d of data){
                ul = makeOneBook(d)
                div.append(ul);
            }
            $('body').append(div);
        }
    })
}

function makeOneBook(book){
    let ul,li,btn1, btn2;
    let titles = ['bookTitle','bookAuthor','bookPrice'];
	let labels = ['제목 : ','작가 : ','가격 : '];
    ul = $('<ul />').attr('id',book.bookNo).html(book.bookNo);
	ul.content = book;
    for(let i = 0;i<titles.length;i++){
        li = $('<li />').attr('id',titles[i]).html(labels[i]+book[titles[i]]);
		ul.append(li);
    }
    btn1 = $('<input />').attr({type:'button',value:'수정',onclick:'modifyCall('+book.bookNo+')'});
    btn2 = $('<input />').attr({type:'button',value:'삭제',onclick:'delBook('+book.bookNo+')'});
    ul.append(btn1,btn2);
    return ul;
}
function modifyCall(id){
    $('#'+id).after($('#modifyBook'));
    $('#modifyBook').css('display','block');
    $('input[name=modifyTitle]').val($('#'+id+' #bookTitle').html().substr(5));
    $('input[name=modifyAuthor]').val($('#'+id+' #bookAuthor').html().substr(5));
    $('input[name=modifyPrice]').val($('#'+id+' #bookPrice').html().substr(5));
    $('input[name=modifyId]').val(id);
}

function delBook(id){
    $.ajax({
        url : '../BookServlet',
        data : {
            cmd : 'delBook',
            id : id
        },
        success : function(data){
            $('#'+id).remove();
        }
    })
}

function addBook(){
    let title, author, price;
    title = $('input[name=addTitle]').val();
    author = $('input[name=addAuthor]').val();
    price = $('input[name=addPrice]').val();
    console.log(title);
    console.log(author);
    console.log(price);

    $.ajax({
        url : '../BookServlet',
        data : {
            cmd : 'addBook',
            title : title,
            author : author,
            price : price
        },
        success : function(data){
            //{bookNo: 5, bookPrice: 15000, bookAuthor: "저자", bookTitle: "책제목"}
            let book = {
                bookNo: data,
                bookPrice : price,
                bookAuthor : author,
                bookTitle : title
            }
            let ul = makeOneBook(book);
            $('#show').append(ul);
        }
    })
}

function modifyBook(){
    let title, author, price, id; 
    title=$('input[name=modifyTitle]').val();
    author=$('input[name=modifyAuthor]').val();
    price=$('input[name=modifyPrice]').val();
    id = $('input[name=modifyId]').val();
    $.ajax({
        url : '../BookServlet',
        data : {
            cmd : 'updateBook',
            title : title,
            author : author,
            price : price,
            id:id
        },
        success: function(){
            $('#'+id+' #bookTitle').html($('input[name=modifyTitle]').val())
            $('#'+id+' #bookAuthor').html($('input[name=modifyAuthor]').val())
            $('#'+id+' #bookPrice').html($('input[name=modifyPrice]').val())    		
        }
    })
}

function cancelModify(){
	$('#modifyBook').css('display','none');
}