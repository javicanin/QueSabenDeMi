// Call the dataTables jQuery plugin
$(document).ready(function () {
  $('#dataTable').DataTable({
    "searching": false,
    "bInfo" : false,
    paging: true,
    ordering: false,
    "aLengthMenu": [[8, 15, 25], [8, 15, 25]],
    "pageLength": 8,
    "language": {
      "url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
    }
  });
});
