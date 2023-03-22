AOS.init({
    duration: 1200,
})
const ctx = document.getElementById("pieChart").getContext('2d');
const myChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: ["Greece", "Bulgaria", "Spain", "Croatia", "France"],
        datasets: [{
            data: [450000, 400000, 1200000, 1100000, 340000],
            backgroundColor: ["#0074D9", "#FF4136", "#2ECC40",
                "#FF851B", "#7FDBFF"]
        }]
    },
});