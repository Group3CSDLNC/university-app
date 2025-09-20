// ================= User dropdown =================
const userInfo = document.querySelector(".user-info");
const userDropdown = document.getElementById("userDropdown");

userInfo.addEventListener("click", () => {
    userDropdown.style.display = userDropdown.style.display === "block" ? "none" : "block";
});

document.addEventListener("click", e => {
    if (!userInfo.contains(e.target)) userDropdown.style.display = "none";
});

// ================= Role & Menu =================
const role = sessionStorage.getItem("role"); // Lấy role từ sessionStorage sau login
const menuMap = {
    Admin: ["home","ctdt","khoahoc","hocphan","hocvien","nhanvien","giangvien","phonghoc","diemthi","luong"],
    QuanLy: ["home","ctdt","khoahoc","hocphan","hocvien"],
    GiangVien: ["home","giangvien","diemthi","luong"],
    SinhVien: ["home","khoahoc","hocphan","diemthi"]
};

const menuItems = document.querySelectorAll(".menu li a");

// Ẩn/hiện menu theo role
menuItems.forEach(a => {
    if (!menuMap[role]?.includes(a.dataset.module)) {
        a.parentElement.style.display = "none";
    }
});

// ================= Load module khi click menu =================
const contentArea = document.getElementById("contentArea");

menuItems.forEach(item => {
    item.addEventListener("click", e => {
        e.preventDefault();
        menuItems.forEach(i => i.parentElement.classList.remove("active"));
        item.parentElement.classList.add("active");
        loadModule(item.dataset.module);
    });
});

// Load mặc định module đầu tiên của role
if (menuMap[role]?.length) {
    loadModule(menuMap[role][0]);
}

// ================= Load module =================
function loadModule(module) {
    fetch(`/module/${module}`)
        .then(res => res.text())
        .then(html => {
            contentArea.innerHTML = html;
            // Gọi JS module tương ứng
            switch(module) {
                case 'ctdt': initCTDTModule(); break;
                case 'hocphan': initHocPhanModule(); break;
                // Thêm module khác nếu cần
            }
        });
}

// ================= Module CTDT =================
function initCTDTModule() {
    const ctdtTableBody = document.getElementById("ctdtTableBody");
    const searchForm = document.getElementById("searchForm");
    const keywordInput = document.getElementById("keywordInput");
    const ctdtModal = document.getElementById("ctdtModal");
    const ctdtForm = document.getElementById("ctdtForm");
    const alertMessage = document.getElementById("alertMessage");

    const loadCTDT = (keyword="") => {
        fetch(`/chuongTrinhDaoTao${keyword ? "?keyword=" + keyword : ""}`)
            .then(res => res.text())
            .then(html => ctdtTableBody.innerHTML = html);
    };

    loadCTDT();

    searchForm?.addEventListener("submit", e => {
        e.preventDefault();
        loadCTDT(keywordInput.value);
    });

    document.getElementById("btnAdd")?.addEventListener("click", () => {
        ctdtForm.reset();
        ctdtModal.style.display = "block";
    });

    document.getElementById("btnClose")?.addEventListener("click", () => {
        ctdtModal.style.display = "none";
    });

    ctdtForm?.addEventListener("submit", e => {
        e.preventDefault();
        fetch("/api/chuongTrinhDaoTao/save", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(Object.fromEntries(new FormData(ctdtForm)))
        })
            .then(res => res.json())
            .then(data => {
                alertMessage.innerText = data.message;
                alertMessage.style.display = "block";
                setTimeout(() => alertMessage.style.display = "none", 2000);
                loadCTDT();
                ctdtModal.style.display = "none";
            });
    });

    ctdtTableBody?.addEventListener("click", e => {
        const target = e.target;
        const id = target.dataset.id;
        if (!id) return;

        if(target.classList.contains("btnEdit")) {
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
            fetch(`/api/chuongTrinhDaoTao/delete/${id}`, { method: "DELETE" })
                .then(res => res.json())
                .then(data => {
                    alertMessage.innerText = data.message;
                    alertMessage.style.display = "block";
                    setTimeout(() => alertMessage.style.display = "none", 2000);
                    loadCTDT();
                });
        }
    });
}

// ================= Module Học phần =================
function initHocPhanModule() {
    const hocphanModal = document.getElementById("hocphanModal");
    const hocphanForm = document.getElementById("hocphanForm");
    const hocphanTableBody = document.getElementById("hocphanTableBody");
    const alertHocPhan = document.getElementById("alertHocPhan");
    const keywordHocPhan = document.getElementById("keywordHocPhan");

    const loadHocPhan = (keyword="") => {
        fetch(`/hocPhan${keyword ? "?keyword=" + keyword : ""}`)
            .then(res => res.text())
            .then(html => hocphanTableBody.innerHTML = html);
    };

    loadHocPhan();

    // Load dropdown Chương trình đào tạo
    fetch('/api/chuongTrinhDaoTao')
        .then(res => res.json())
        .then(ctdts => {
            const maCTDTSelect = document.getElementById("maCTDT");
            maCTDTSelect.innerHTML = `<option value="">-- Chọn CTDT --</option>`;
            ctdts.forEach(ct => {
                const opt = document.createElement("option");
                opt.value = ct.maCTDT;
                opt.text = ct.tenCTDT;
                maCTDTSelect.appendChild(opt);
            });
        });

    // Load dropdown Môn tiên quyết
    fetch('/api/hocPhan')
        .then(res => res.json())
        .then(hps => {
            const tienQuyetSelect = document.getElementById("tienQuyet");
            tienQuyetSelect.innerHTML = `<option value="0">Không có</option>`;
            hps.forEach(hp => {
                const opt = document.createElement("option");
                opt.value = hp.maHP;
                opt.text = hp.tenHP;
                tienQuyetSelect.appendChild(opt);
            });
        });

    document.getElementById("searchHocPhanForm")?.addEventListener("submit", e => {
        e.preventDefault();
        loadHocPhan(keywordHocPhan.value);
    });

    document.getElementById("btnAddHocPhan")?.addEventListener("click", () => {
        hocphanForm.reset();
        document.getElementById("maHP").value = "";
        hocphanModal.style.display = "block";
    });

    document.getElementById("btnCloseHocPhan")?.addEventListener("click", () => {
        hocphanModal.style.display = "none";
    });

    hocphanForm?.addEventListener("submit", e => {
        e.preventDefault();
        fetch("/api/hocPhan/save", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(Object.fromEntries(new FormData(hocphanForm)))
        })
            .then(res => res.json())
            .then(data => {
                alertHocPhan.innerText = data.message;
                alertHocPhan.style.display = "block";
                setTimeout(() => alertHocPhan.style.display = "none", 2000);
                loadHocPhan();
                hocphanModal.style.display = "none";
            });
    });

    hocphanTableBody?.addEventListener("click", e => {
        const target = e.target;
        const maHP = target.dataset.id;
        if (!maHP) return;

        if(target.classList.contains("btnEdit")) {
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
            fetch(`/api/hocPhan/delete/${maHP}`, { method: "DELETE" })
                .then(res => res.json())
                .then(data => {
                    alertHocPhan.innerText = data.message;
                    alertHocPhan.style.display = "block";
                    setTimeout(() => alertHocPhan.style.display = "none", 2000);
                    loadHocPhan();
                });
        }
    });
}
