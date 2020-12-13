import React, {Component} from 'react';
import './home-page.css'
import HeaderComponent from "./HeaderComponent";

class HomePage extends Component {
    constructor(props) {
        super(props);
    }



    render() {
        return (

            <div className="container">
                <HeaderComponent/>
                <div className="card-deck">
                    <div className="row">
                        <div className="card-group">
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div  className="card-body">
                                        <button onClick={this.goCart}><img
                                            src="http://www.simpleimageresizer.com/_uploads/photos/0f72a54e/cart_2_300x200.jpg"
                                            className="card-img" ></img></button>

                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div className="card-body">
                                        <img
                                            src="http://www.simpleimageresizer.com/_uploads/photos/0f72a54e/table_300x200.jpg"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div className="card-body">
                                        <img
                                            src="http://www.simpleimageresizer.com/_uploads/photos/0f72a54e/report_300x200.png"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div className="card-body">
                                        <img
                                            src="http://www.simpleimageresizer.com/_uploads/photos/0f72a54e/product_1_300x200.png"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div className="card-body">
                                        <img
                                            src="http://www.simpleimageresizer.com/_uploads/photos/0f72a54e/users_300x200.png"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">

                                    <div className="card-body">
                                        <img
                                            src="https://www.ronnefeldt.com.tr/wp-content/plugins/instagram-feed/img/placeholder.png"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div className="card-body">
                                        <img
                                            src="https://www.ronnefeldt.com.tr/wp-content/plugins/instagram-feed/img/placeholder.png"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div className="card-body">
                                        <img
                                            src="https://www.ronnefeldt.com.tr/wp-content/plugins/instagram-feed/img/placeholder.png"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-4">
                                <div className="card bg-light mb-4">
                                    <div className="card-body">
                                        <img
                                            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgR5Dgsmqlk7pSVXcHxf3JzhTfLZtY9H9qHw&usqp=CAU"
                                            className="card-img"></img>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default HomePage;
