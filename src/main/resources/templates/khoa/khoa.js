const modal = document.getElementById("khoaModal");
const form = document.getElementById("khoaForm");

document.getElementById("btnAdd").addEventListener("click", () => {
    form.reset();
    document.getElementById("maKhoa").value = "";
    modal.style.display = "block";
});

document.getElementById("btnClose").addEventListener("click", () => {
    modal.style.display = "none";
});

// Lưu (thêm/sửa)
form.addEventListener("submit", e => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(form));
    fetch("/api/khoa/save", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }).then(res => res.json())
        .then(r => {
            alert(r.message);
            location.reload();
        });
});

// Edit
document.querySelectorAll(".btnEdit").forEach(btn => {
    btn.addEventListener("click", () => {
        const id = btn.getAttribute("data-id");
        fetch(`/api/khoa/${id}`).then(res => res.json()).then(k => {
            document.getElementById("maKhoa").value = k.maKhoa;
            document.getElementById("tenKhoa").value = k.tenKhoa;
            document.getElementById("email").value = k.email;
            modal.style.display = "block";
        });
    });
});

// Delete
document.querySelectorAll(".btnDelete").forEach(btn => {
    btn.addEventListener("click", () => {
        if (!confirm("Xóa khoa này?")) return;
        const id = btn.getAttribute("data-id");
        fetch(`/api/khoa/delete/${id}`, { method: "DELETE" })
            .then(res => res.json()).then(r => {
            alert(r.message);
            location.reload();
        });
    });
});
