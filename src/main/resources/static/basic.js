let targetId;

$(document).ready(function () {
    $('#query').on('keypress', function (e) {
        if (e.key == 'Enter') {
            execSearch();
        }
    });
    $('#close').on('click', function () {
        $('#container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function () {
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active');

        $('#see-area').show();
        $('#search-area').hide();
    })
    $('.nav div.nav-search').on('click', function () {
        $('div.nav-see').removeClass('active');
        $('div.nav-search').addClass('active');

        $('#see-area').hide();
        $('#search-area').show();
    })

    $('#see-area').show();
    $('#search-area').hide();

    showProduct();
})


function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//검색어 입력 받아서 HTML로 만들고 붙이기
function execSearch() {
   
    let query = $('#query').val();

    if (query == '') {
        alert('검색어를 입력해주세요');
        $('#query').focus();
        return;
    }
    $.ajax({
        type: 'GET',
        url: `/api/search?query=${query}`,
        success: function (response) {
            $('#search-result-box').empty();
            // 4. for 문마다 itemDto를 꺼내서 HTML 만들고 검색결과 목록에 붙이기!
            for (let i = 0; i < response.length; i++) {
                let itemDto = response[i];
                let tempHtml = addHTML(itemDto);
                $('#search-result-box').append(tempHtml);
            }
        }
    })

}

//각각의 HTML 생성
function addHTML(itemDto) {
    return `<div class="search-itemDto">
        <div class="search-itemDto-left">
            <img src="${itemDto.image}" alt="">
        </div>
        <div class="search-itemDto-center">
            <div>${itemDto.title}</div>
            <div class="price">
                ${numberWithCommas(itemDto.lprice)}
                <span class="unit">원</span>
            </div>
        </div>
        <div class="search-itemDto-right">
            <img src="images/icon-save.png" alt="" onclick='addProduct(${JSON.stringify(itemDto)})'>
        </div>
    </div>`
}

//관심목록에 추가
function addProduct(itemDto) {
    $.ajax({
        type: "POST",
        url: '/api/products',
        contentType: "application/json",
        data: JSON.stringify(itemDto),
        success: function (response) {
            $('#container').addClass('active');
            targetId = response.id; 
        }
    })
}

function showProduct() {
    $.ajax({
        type: 'GET',
        url: '/api/products',
        success: function (response) {
            // 2. 관심상품 목록, 검색결과 목록 비우기
            $('#product-container').empty();
            $('#search-result-box').empty();
            // 3. for 문마다 관심 상품 HTML 만들어서 관심상품 목록에 붙이기
            for (let i = 0; i < response.length; i++) {
                let product = response[i];
                let tempHtml = addProductItem(product);
                $('#product-container').append(tempHtml);
            }
        }
    })
}

function addProductItem(product) {
    return `<div class="product-card" onclick="window.location.href='${product.link}'">
                <div class="card-header">
                    <img src="${product.image}"
                         alt="">
                </div>
                <div class="card-body">
                    <div class="title">
                        ${product.title}
                    </div>
                    <div class="lprice">
                        <span>${numberWithCommas(product.lprice)}</span>원
                    </div>
                    <div class="isgood ${product.lprice > product.myprice ? 'none' : ''}">
                        최저가
                    </div>
                </div>
            </div>`;
}

function setMyprice() {

    let myprice = $('#myprice').val();
    //예외처리
    if (myprice == '') {
        alert('올바른 가격을 입력해주세요');
        return;
    }

    $.ajax({
        type: "PUT",
        url: `/api/products/${targetId}`,
        contentType: "application/json",
        data: JSON.stringify({myprice: myprice}),
        success: function (response) {
            // 모달 종료
            $('#container').removeClass('active');
            alert('성공적으로 등록되었습니다.');
            window.location.reload();
        }
    })
}
