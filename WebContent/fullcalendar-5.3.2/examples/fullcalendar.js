//modal javascript
function showModal(args) {
	document.querySelector('#startDate').value = args.startStr;
	document.querySelector('#endDate').min =args.startStr;
	document.querySelector('#endDate').value =args.endStr;
	// Get the modal
	var modal = document.getElementById("myModal");
	modal.style.display = "block";

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];


	// When the user clicks on <span> (x), close the modal
	span.onclick = function () {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function (event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
	var saveBtn = document.getElementById('saveBtn');
	saveBtn.onclick = function () {
		let title = document.querySelector('#title').value;
		let start = document.querySelector('#startDate').value;
		let end = document.querySelector('#endDate').value;
		console.log(title,start,end);
		if (title) {
			calendar.addEvent({
				title: title,
				start: start,//arg.start,
				end: end,//arg.end,
				allDay: false//arg.allDay
			})
			//db에 새로운 한건 등록. ㅡ> 서블릿
			//let start = arg.start;
			//start = start.getFullYear()+ '-'+ (start.getMonth()+1)+'-'+start.getDate();
			//let end = arg.end;
			//end = end.getFullYear()+ '-'+ (end.getMonth()+1)+'-'+end.getDate();
			//console.log(arg.start, arg.end);
			createSchedule(title, start, end);
		}
		calendar.unselect()
		modal.style.display = "none";
	}
}

//1 title, start_date, end_date 가지고 오는 method
//2 json 타입으로 [{title, start, end},{},{}]
//3 events : javascript의 object타입으로 할당.

var calendar;

//function contentLoadFunc(){
document.addEventListener('DOMContentLoaded', function () {
	var calendarEl = document.getElementById('calendar');
	let eve = [];
	let xhtp = new XMLHttpRequest();
	xhtp.onreadystatechange = function () {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			//console.log(xhtp.response);
			let data = xhtp.response;
			data.forEach(function (item) {
				eve.push(item);
			})
			test();
		}
	}
	xhtp.open('get', '../../GetScheduleServlet');
	xhtp.responseType = 'json';
	xhtp.send();

	function test() {
		calendar = new FullCalendar.Calendar(calendarEl, {
			headerToolbar: {
				left: 'prev,next today',
				center: 'title',
				right: 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			initialDate: '2020-11-12',
			navLinks: true, // can click day/week names to navigate views
			selectable: true,
			selectMirror: true,
			select: function (arg) {
				//var title = prompt('Event Title:');
				//var start = prompt('시작일정:');
				//var end = prompt('종료일정');

				showModal(arg);



			},
			eventClick: function (arg) {
				if (confirm('Are you sure you want to delete this event?')) {
					//console.log(arg);
					let start = arg.event.start;
					//if(start.getDate()<10){
					//	start = start.getFullYear()+ '-'+ (start.getMonth()+1)+'-0'+start.getDate();
					//}else{
					start = start.getFullYear() + '-' + (start.getMonth() + 1) + '-' + start.getDate();
					//}
					deleteSchedule(arg.event.title, start);
					arg.event.remove()
					//db에서 한건삭제. ㅡ> 서블릿
				}
			},
			editable: true,
			dayMaxEvents: true, // allow "more" link when too many events
			events: eve
		}); //calendar() 메소드호출;

		calendar.render();
	}
});

function createSchedule(v1, v2, v3) {
	let xhtp = new XMLHttpRequest();
	xhtp.onreadystatechange = function () {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			console.log(xhtp);
		}
	}
	console.log(v1 + " " + v2 + " " + v3)
	xhtp.open('get', '../../PutScheduleServlet?title=' + v1 + '&start=' + v2 + '&end=' + v3)
	xhtp.send();
}

function deleteSchedule(t, s) {
	let xhtp = new XMLHttpRequest();
	xhtp.onreadystatechange = function () {
		if (xhtp.readyState == 4 && xhtp.status == 200) {

		}
	}
	xhtp.open('get', 'DelScheduleServlet?title=' + t + '&start=' + s);
	xhtp.send();
}
/* events: [
	        {
	          title: 'All Day Event',
	          start: '2020-09-01',
			  end: '2020-09-15'
	        },
	        {
	          title: 'Long Event',
	          start: '2020-09-07',
	          end: '2020-09-10'
	        },
	        {
	          groupId: 999,
	          title: 'Repeating Event',
	          start: '2020-09-09T16:00:00'
	        },
	        {
	          groupId: 999,
	          title: 'Repeating Event',
	          start: '2020-09-16T16:00:00'
	        },
	        {
	          title: 'Conference',
	          start: '2020-09-11',
	          end: '2020-09-13'
	        },
	        {
	          title: 'Meeting',
	          start: '2020-09-12T10:30:00',
	          end: '2020-09-12T12:30:00'
	        },
	        {
	          title: 'Lunch',
	          start: '2020-09-12T12:00:00'
	        },
	        {
	          title: 'Meeting',
	          start: '2020-09-12T14:30:00'
	        },
	        {
	          title: 'Happy Hour',
	          start: '2020-09-12T17:30:00'
	        },
	        {
	          title: 'Dinner',
	          start: '2020-09-12T20:00:00'
	        },
	        {
	          title: 'Birthday Party',
	          start: '2020-09-13T07:00:00'
	        },
	        {
	          title: 'Click for Google',
	          url: 'http://google.com/',
	          start: '2020-09-28'
	        }
	      ]*/


//}