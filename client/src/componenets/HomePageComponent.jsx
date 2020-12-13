import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import testabc from "./testabc";
class HomePageComponent extends Component {
    constructor(props) {
        super(props);
    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('/');
        }

    }
    logout=(e)=>{
        e.preventDefault();
        localStorage.removeItem("username");
        localStorage.removeItem("password");
        window.location.reload();
    }


    render() {

        return (

            <div className="social-box">

                <div className="container">
                    <HeaderComponent/>
                    <div className="row">
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/products"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">CART </h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/places"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">TABLES </h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">REPORTS </h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">PRODUCTS </h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">USERS </h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">&nbsp;&nbsp;</h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">&nbsp;&nbsp;</h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">&nbsp;&nbsp;</h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-4 col-xs-12 text-center">
                            <div className="box">
                                <div className="box-btn">
                                    <a onClick={this.logout}><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                        <div className="box-title">
                                            <h3 className="box-text1">EXİT</h3>
                                        </div>
                                        <div className="box-text">
                                            <span></span>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}
export default HomePageComponent;