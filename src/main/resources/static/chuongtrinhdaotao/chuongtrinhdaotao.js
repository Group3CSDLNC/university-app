document.addEventListener("DOMContentLoaded", function() {
    const ctdtTableBody = document.getElementById("ctdtTableBody");
    const searchForm = document.getElementById("searchForm");
    const keywordInput = document.getElementById("keywordInput");
    const ctdtModal = document.getElementById("ctdtModal");
    const ctdtForm = document.getElementById("ctdtForm");
    const alertMessage = document.getElementById("alertMessage");

    function loadCTDT(keyword="") {
        // Gọi controller Spring trả về partial tbody
        fetch(`/chuongTrinhDaoTao/table${keyword ? "?keyword=" + keyword : ""}`)
            .then(res => res.text())
            .then(html => ctdtTableBody.innerHTML = html);
    }

    loadCTDT(); // load mặc định

    searchForm.addEventListener("submit", e => {
        e.preventDefault();
        loadCTDT(keywordInput.value);
    });

    document.getElementById("btnAdd").addEventListener("click", () => {
        ctdtForm.reset();
        ctdtModal.style.display = "block";
    });

    document.getElementById("btnClose").addEventListener("click", () => {
        ctdtModal.style.display = "none";
    });

    ctdtForm.addEventListener("submit", e => {
        e.preventDefault();
        const formData = new FormData(ctdtForm);
        fetch("/api/chuongTrinhDaoTao/save", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(Object.fromEntries(formData))
        })
            .then(res => res.json())
            .then(data => {
                // Khi hiển thị thông báo thành công
                alertMessage.innerText = data.message;
                alertMessage.style.display = "block";

                // Ẩn sau 1 giây
                setTimeout(() => {
                    alertMessage.style.display = "none";
                }, 1000);

                loadCTDT();
                ctdtModal.style.display = "none";
            });
    });

    // delegation cho Edit/Delete
    ctdtTableBody.addEventListener("click", e => {
        const target = e.target;
        if(target.classList.contains("btnEdit")) {
            const id = target.dataset.id;
            fetch(`/api/chuongTrinhDaoTao/${id}`)
                .then(res => res.json())
                .then(ct => {
                    document.getElementById("maCTDT").value = ct.maCTDT;
                    document.getElementById("tenCTDT").value = ct.tenCTDT;
                    document.getElementById("tongTinChi").value = ct.tongTinChi;
                    document.getElementById("maCN").value = ct.maCN;
                    document.getElementById("namHoc").value = ct.namHoc;
                    ctdtModal.style.display = "block";
                });
        } else if(target.classList.contains("btnDelete")) {
            if(!confirm("Bạn có chắc muốn xóa CTDT này?")) return;
            const id = target.dataset.id;
            fetch(`/api/chuongTrinhDaoTao/delete/${id}`, { method: "DELETE" })
                .then(res => res.json())
                .then(data => {
                    alertMessage.innerText = data.message;
                    alertMessage.style.display = "block";
                    loadCTDT();
                });
        }
    });
});
