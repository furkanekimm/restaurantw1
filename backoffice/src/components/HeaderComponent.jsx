import React, {Component} from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)


    }
    logout=(e)=>{
        e.preventDefault();
        localStorage.removeItem("username");
        localStorage.removeItem("password");
        window.location.reload();
    }
    render() {
        return (
            /*<div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="/product" className="navbar-brand">Products</a></div>
                        <div><a href="/listuser" className="navbar-brand">Users</a></div>
                        <form className="form-inline my-2 my-lg-0">
                                <button className="btn btn-outline-success my-2 my-sm-0" onClick={this.logout}>Logout</button>
                        </form>

                    </nav>
                </header>
            </div>*/
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon">asdsad</span>
                </button>
                <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <a className="navbar-brand" href="#">Admin Control</a>
                    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li className="nav-item active">
                            <a className="nav-link" href="/product" >Products <span className="sr-only">(current)</span></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/listuser">Users</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/listroles">Roles</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/listcategories">Categories</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/listwaiters">Waiters</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/carts">Reports</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/createmedia">Medias</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/infoserver">Info</a>
                        </li>

                        <li className="nav-item">
                            <a className="nav-link " href="/listplace">TablePlaces</a>
                        </li>
                    </ul>
                    <form className="form-inline my-2 my-lg-0">
                            <button className="btn btn-outline-danger my-2 my-sm-0" type="submit" onClick={this.logout}>Logout</button>
                    </form>
                </div>
            </nav>
        );
    }
}

export default HeaderComponent;