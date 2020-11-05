$.ajax({
    url: '../public/MOCK_DATA.json',
    dataType: 'json',
    success: function (data) {
        console.log(data);
        let first = true;
        for (d of data) {
            if (first) {
                outt(d);
                first = false;
            }
            inn(d)
        }
    }

})

function outt(data) {
    let div = $("#show");
    let allDel = $('<button />').attr('class', 'allDel').html("삭제");
    allDel.on('click', selectDel)
    div.append(allDel);
    let table = $('<table border=1 />');
    table.attr('id', 'tb');
    let tr = $('<tr />');
    let check = $('<input />').attr('type', 'checkbox').attr('class', 'allck');
    $(check).change(function () {
        if ($(this).is(":checked")) {
            allc()
        } else {
            alluc()
        }
    })
    let th = $('<th />').append(check);
    tr.append(th);
    for (a in data) {
        th = $('<th />').html(a);
        tr.append(th);
    }
    th = $('<th />').html("button");
    tr.append(th);
    table.append(tr);
    div.append(table);
}

function inn(data) {
    let table = $('#tb');
    let tr = $('<tr />');
    let input = $('<input />').attr('type', 'checkbox').attr('class', 'ch').attr('id', 'idCh');
    input.on('click', check);
    let td = $('<td />').append(input);

    tr.append(td);
    for (a in data) {
        td = $('<td />').html(data[a]);
        tr.append(td);
    }
    let btn = $('<input />').attr({
        'type': 'button'
    }).attr({
        'value': 'Capy'
    }).attr("id", data.id);
    $(btn).on('click', del)
    td = $('<th />').append(btn);
    tr.append(td);
    table.append(tr);

    function del() {
        //console.log(this.id);
        let tr = $('<tr />');
        let input = $('<input />').attr('type', 'checkbox').attr('class', 'ch').attr('id', 'idCh');
        input.on('click', check);
        let td = $('<td />').append(input);
        tr.append(td);
        for (a in data) {
            td = $('<td />').html(data[a]);
            tr.append(td);
        }
        let btn = $('<input />').attr({
            'type': 'button'
        }).attr({
            'value': 'Capy'
        }).attr("id", data.id);
        $(btn).on('click', del)
        td = $('<th />').append(btn);
        tr.append(td);
        //table.append(tr);
        $(this).parent().parent().after(tr);
    }
}


function allc() {
    let check = $('input.ch');
    for (ch of check) {
        // if($(ch).is(':checked')==false){}
        $(ch).prop('checked', true);
    }
}

function alluc() {
    let check = $('input.ch');
    let checkId = $('td>#idCh');
    let checkOne = $('#idCh');
    console.log(check, checkId, checkOne);
    for (ch of check) {
        // if($(ch).is(':checked')==false){}
        $(ch).prop('checked', false);
    }
}

function selectDel() {
    let check = $('input.ch');
    for (ch of check) {
        if ($(ch).is(":checked") == true) {
            $(ch).parent().parent().remove();
        }
    }
}

function check() {
    let check = $('input.ch');
    for (ck of check) {
        if ($(ck).is(":checked") == false) {
            $('.allck').prop('checked', false);
            return;
        } 
        $('.allck').prop('checked', true);

    }
    // if ($('input.ch').is(":checked") == false) {
    //     $('.allck').prop('checked', false);
    //     return;
    // }
    // $('.allck').prop('checked', true);

}