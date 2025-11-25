<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
.card {
    transition: all 0.3s ease;
}
.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 6px 20px rgba(0,0,0,0.15);
}
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<jsp:include page="/WEB-INF/views/layout/navbar.jsp" />

<div class="container container-main">
    <!-- CONTENT BLOCK -->
    <jsp:include page="${contentPage}" />
</div>
<script>
document.addEventListener("DOMContentLoaded", function () {

    const selectedSeats = new Set();

    const seats = document.querySelectorAll('.seat');
    console.log("Seats found:", seats.length); // Debug

    seats.forEach(seat => {
        seat.addEventListener('click', () => {
            seat.classList.toggle('selected');

            const id = seat.dataset.id;

            if (selectedSeats.has(id)) {
                selectedSeats.delete(id);
            } else {
                selectedSeats.add(id);
            }

            const input = document.getElementById('seatIds');
            if (input) {
                input.value = Array.from(selectedSeats);
            }
        });
    });

});
</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />
