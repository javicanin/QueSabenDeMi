// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    labels: labelsDisp,
    datasets: [{
      data: datosDisp,
      backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
      hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
      hoverBorderColor: "rgba(234, 236, 244, 1)",
    }],
  },
  options: {
    title:{
      display: true,
      fontSize: 16,
      fontStyle: 'normal',
      position: 'top',
      text: 'ordenador o teléfono',
      fontColor: '#4e73df'
    },
    maintainAspectRatio: false,
    responsive: true,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: true,
      caretPadding: 10,
    },
    legend: {
      display: true,
      position: 'bottom',
      labels: {
          fontSize: 12,
          boxWidth: 12,
          padding: 10,
      }
    },
    cutoutPercentage: 0,
  },
});
