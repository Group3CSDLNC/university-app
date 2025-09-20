const cnModal = document.getElementById("cnModal");
const cnForm = document.getElementById("cnForm");

document.getElementById("btnAdd").addEventListener("click", () => {
    cnForm.reset();
    document.getElementById("maCN").value = "";
    cnModal.style.display = "block";
});

document.getElementById("btnClose").addEventListener("click", () => {
    cnModal.style.display = "none";
});

// Lưu
cnForm.addEventListener("submit", e => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(cnForm));
    fetch("/api/chuyennganh/save", {
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
        fetch(`/api/chuyennganh/${id}`).then(res => res.json()).then(cn => {
            document.getElementById("maCN").value = cn.maCN;
            document.getElementById("tenCN").value = cn.tenCN;
            document.getElementById("maKhoa").value = cn.maKhoa;
            cnModal.style.display = "block";
        });
    });
});

// Delete
document.querySelectorAll(".btnDelete").forEach(btn => {
    btn.addEventListener("click", () => {
        if (!confirm("Xóa chuyên ngành này?")) return;
        const id = btn.getAttribute("data-id");
        fetch(`/api/chuyennganh/delete/${id}`, { method: "DELETE" })
            .then(res => res.json()).then(r => {
            alert(r.message);
            location.reload();
        });
    });
});
