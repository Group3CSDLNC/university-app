const hocphanModal = document.getElementById("hocphanModal");
const hocphanForm = document.getElementById("hocphanForm");
const hocphanTableBody = document.getElementById("hocphanTableBody");
const alertHocPhan = document.getElementById("alertHocPhan");
const keywordHocPhan = document.getElementById("keywordHocPhan");

// Load danh sách Học phần
function loadHocPhan(keyword="") {
    fetch(`/hocPhan/list${keyword ? "?keyword=" + keyword : ""}`)
        .then(res => res.text())
        .then(html => hocphanTableBody.innerHTML = html);
}

loadHocPhan();

// Tìm kiếm
document.getElementById("searchHocPhanForm").addEventListener("submit", e => {
    e.preventDefault();
    loadHocPhan(keywordHocPhan.value);
});

// Mở modal Thêm
document.getElementById("btnAddHocPhan").addEventListener("click", () => {
    hocphanForm.reset();
    document.getElementById("maHP").value = "";
    hocphanModal.style.display = "block";
});

// Đóng modal
document.getElementById("btnCloseHocPhan").addEventListener("click", () => {
    hocphanModal.style.display = "none";
});

// Submit thêm/sửa
hocphanForm.addEventListener("submit", e => {
    e.preventDefault();
    const formData = new FormData(hocphanForm);
    fetch("/api/hocPhan/save", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(Object.fromEntries(formData))
    })
        .then(res => res.json())
        .then(data => {
            alertHocPhan.innerText = data.message;
            alertHocPhan.style.display = "block";
            setTimeout(() => alertHocPhan.style.display = "none", 1000);
            loadHocPhan();
            hocphanModal.style.display = "none";
        });
});

// Edit/Delete (delegation)
hocphanTableBody.addEventListener("click", e => {
    const target = e.target;
    if(target.classList.contains("btnEdit")) {
        const maHP = target.dataset.id;
        fetch(`/api/hocPhan/${maHP}`)
            .then(res => res.json())
            .then(hp => {
                document.getElementById("maHP").value = hp.maHP;
                document.getElementById("tenHP").value = hp.tenHP;
                document.getElementById("soTinChi").value = hp.soTinChi;
                document.getElementById("tietTH").value = hp.tietTH;
                document.getElementById("tietLT").value = hp.tietLT;
                document.getElementById("maCTDT").value = hp.maCTDT;
                document.getElementById("tienQuyet").value = hp.tienQuyet || 0;
                hocphanModal.style.display = "block";
            });
    } else if(target.classList.contains("btnDelete")) {
        if(!confirm("Bạn có chắc muốn xóa học phần này?")) return;
        const maHP = target.dataset.id;
        fetch(`/api/hocPhan/delete/${maHP}`, { method: "DELETE" })
            .then(res => res.json())
            .then(data => {
                alertHocPhan.innerText = data.message;
                alertHocPhan.style.display = "block";
                setTimeout(() => alertHocPhan.style.display = "none", 1000);
                loadHocPhan();
            });
    }
});
