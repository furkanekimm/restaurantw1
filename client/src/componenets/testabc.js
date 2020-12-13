class Test {

    addTable(count) {

        const div = document.querySelector(".row");
        console.log(count);

        for (let i = 1; i <= count; i++) {
            div.innerHTML += `
                                <div class="col-lg-4 col-xs-12 text-center">
                                        <div class="box">
                                            <div class="box-btn">
                                                <a onClick={goCart(${i})}><i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                                                    <div class="box-title">
                                                        <h3 class="box-text1">Masa ${i}</h3>
                                                    </div>
                                                    <div class="box-text">
                                                        <span></span>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                `;
        }
        console.log(div);

    }
}

export default new Test();

