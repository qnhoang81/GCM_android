/**
 * Created with IntelliJ IDEA.
 * User: qnhoang81
 * Date: 9/10/13
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
<script type="text/javascript">
// <![CDATA[
    gcjQuery(document).ready(function(){
        var today = new Date();
        var tmpYear = today.getFullYear();
        var tmpMonth = today.getMonth();
        var tmpDay = today.getDate();
        var tmpView = 'month';
        var vars = window.location.hash.replace(/&amp;/gi, "&").split("&");
        for ( var i = 0; i < vars.length; i++ ){
            if(vars[i].match("^#year"))tmpYear = vars[i].substring(6);
            if(vars[i].match("^month"))tmpMonth = vars[i].substring(6)-1;
            if(vars[i].match("^day"))tmpDay = vars[i].substring(4);
            if(vars[i].match("^view"))tmpView = vars[i].substring(5);
        }
        if (gcjQuery(document).width() < 500) {tmpView = 'list';}
        gcjQuery('#gcalendar_component').fullCalendar({
            header: {
                left: 'prev,next ',
                center: 'title',
                right: 'month,agendaWeek,agendaDay,list'
            },
            year: tmpYear,
            month: tmpMonth,
            date: tmpDay,
            defaultView: tmpView,
            weekNumbers: false,
            weekNumberTitle: '',
            editable: false, theme: false,
            weekends: true,
            titleFormat: {
                month: 'MMMM yyyy',
                week: "MMM d[ yyyy]{ '-'[ MMM] d yyyy}",
                day: 'dddd, MMM d, yyyy',
                list: 'MMM d yyyy'},
            firstDay: 0,
            firstHour: 6,
            maxTime: 24,
            minTime: 0,
            monthNames: ['January','February','March','April','May','June','July','August','September','October','November','December'],
            monthNamesShort: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
            dayNames: ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'],
            dayNamesShort: ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'],
            dayNamesShort: ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'],
            timeFormat: {
                month: 'h:mm tt{ - h:mm tt}',
                week: "h:mm tt{ - h:mm tt}",
                day: 'h:mm tt{ - h:mm tt}',
                list: 'h:mm tt{ - h:mm tt}'},
            columnFormat: { month: 'ddd', week: 'ddd d', day: 'dddd d'},
            axisFormat: 'h:mm tt',
            allDayText: 'all day',
            buttonText: {
                today:    'Today',
                month:    'Month',
                week:     'Week',
                day:      'Day',
                list:      'List'
            },
            listSections: 'smart',
            listRange: 30,
            listPage: 30,
            listTexts: {
                until: 'Until',
                past: 'Past events',
                today: 'Today',
                tomorrow: 'Tomorrow',
                thisWeek: 'This week',
                nextWeek: 'Next week',
                thisMonth: 'This month',
                nextMonth: 'Next month',
                future: 'Future events',
                week: 'W'
            },
            eventSources: [
                '/calendar/jsonfeed?format=raw&gcid=1'	],
            viewDisplay: function(view) {
                var d = gcjQuery('#gcalendar_component').fullCalendar('getDate');
                var newHash = 'year='+d.getFullYear()+'&month='+(d.getMonth()+1)+'&day='+d.getDate()+'&view='+view.name;
                if(window.location.hash.replace(/&amp;/gi, "&") != newHash)
                    window.location.hash = newHash;
            },
            eventRender: function(event, element) {
                if (event.description){
                    element.tipTip({content: event.description, defaultPosition: 'top'});}
            },
            eventClick: function(event, jsEvent, view) {gcjQuery('#tiptip_holder').hide();
                window.location = gcEncode(event.url); return false;
            },
            dayClick: function(date, allDay, jsEvent, view) {
                dayClickCustom(date, allDay, jsEvent, view);
            },
            eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
                eventDropCustom(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view);
            },
            eventResize: function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view) {
                eventResizeCustom(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view);
            },
            loading: function(bool) {
                if (bool) {
                    gcjQuery('#gcalendar_component_loading').show();
                }else{
                    gcjQuery('#gcalendar_component_loading').hide();
                }
            }
        });
        var custom_buttons = '<span class="fc-button fc-button-datepicker fc-state-default fc-corner-left fc-corner-right">'+
                '<span class="fc-button-inner"><span class="fc-button-content" id="gcalendar_component_date_picker_button">'+
                '<input type="hidden" id="gcalendar_component_date_picker" value="">'+
                '<i class="icon-calendar" title="Jump"></i>'+
                '</span></span></span></span>';
        custom_buttons +='<span class="hidden-phone fc-button fc-button-print fc-state-default fc-corner-left fc-corner-right">'+
                '<span class="fc-button-inner"><span class="fc-button-content" id="gcalendar_component_print_button">'+
                '<i class="icon-print" title="Print"></i>'+
                '</span></span></span></span>';
        gcjQuery('span.fc-header-space').after(custom_buttons);
        if (gcjQuery('table').disableSelection) gcjQuery('div.fc-header-space').closest('table.fc-header').disableSelection();
        gcjQuery("#gcalendar_component_date_picker").datepicker({
            dateFormat: 'dd-mm-yy',
            changeYear: true,
            dayNames: ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'],
            dayNamesShort: ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'],
            dayNamesMin: ['Su','Mo','Tu','We','Th','Fr','Sa'],
            monthNames: ['January','February','March','April','May','June','July','August','September','October','November','December'],
            monthNamesShort: ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
            onSelect: function(dateText, inst) {
                var d = gcjQuery('#gcalendar_component_date_picker').datepicker('getDate');
                var view = gcjQuery('#gcalendar_component').fullCalendar('getView').name;
                gcjQuery('#gcalendar_component').fullCalendar('gotoDate', d);
            }
        });
        gcjQuery(window).bind( 'hashchange', function(){
            var today = new Date();
            var tmpYear = today.getFullYear();
            var tmpMonth = today.getMonth();
            var tmpDay = today.getDate();
            var tmpView = 'month';
            var vars = window.location.hash.replace(/&amp;/gi, "&").split("&");
            for ( var i = 0; i < vars.length; i++ ){
                if(vars[i].match("^#year"))tmpYear = vars[i].substring(6);
                if(vars[i].match("^month"))tmpMonth = vars[i].substring(6)-1;
                if(vars[i].match("^day"))tmpDay = vars[i].substring(4);
                if(vars[i].match("^view"))tmpView = vars[i].substring(5);
            }
            var date = new Date(tmpYear, tmpMonth, tmpDay,0,0,0);
            var d = gcjQuery('#gcalendar_component').fullCalendar('getDate');
            var view = gcjQuery('#gcalendar_component').fullCalendar('getView');
            if(date.getFullYear() != d.getFullYear() || date.getMonth() != d.getMonth() || date.getDate() != d.getDate())
                gcjQuery('#gcalendar_component').fullCalendar('gotoDate', date);
            if(view.name != tmpView)
                gcjQuery('#gcalendar_component').fullCalendar('changeView', tmpView);
        });
    });
    var dayClickCustom = function(date, allDay, jsEvent, view){gcjQuery('#gcalendar_component').fullCalendar('gotoDate', date).fullCalendar('changeView', 'agendaDay');}
    var eventDropCustom = function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){};
    var eventResizeCustom = function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view){};
    // ]]>

jQuery(document).ready(function()
    {
        jQuery('.hasTooltip').tooltip({"container": false});
});
</script>