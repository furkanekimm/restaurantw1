import React, {Component} from 'react';
import {useHistory} from 'react-router-dom';
const HeaderComponent=(props)=>{
    const history = useHistory();
    const logout=(e)=>{
        localStorage.removeItem("username");
        localStorage.removeItem("password");
        history.push('/');
    }
    const goCategories=(e)=>{
        history.push('/categories');
    }
    const goProducts=(e)=>{
        history.push('/products');
    }

    const goWaiters=(e)=>{
        history.push('/waiters');
    }

    const goUsers=(e)=>{
        history.push('/users');
    }

    const goRoles=(e)=>{
        history.push('/roles');
    }

    const goMedias=(e)=>{
        history.push('/createmedia');
    }

    const goReports=(e)=>{
        history.push('/reports');
    }

    const goInfos=(e)=>{
        history.push('/infos');
    }

    const goTables=(e)=>{
        history.push('/tables');
    }

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
                        <a className="nav-link" href="#" onClick={(e)=>goProducts(e)} >Products <span className="sr-only">(current)</span></a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goUsers(e)}>Users</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goRoles(e)}>Roles</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link"  onClick={(e)=>goCategories(e)}>Categories</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" onClick={(e)=>goWaiters(e)}>Waiters</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goReports(e)}>Reports</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goMedias(e)} >Medias</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={(e)=>goInfos(e)}>Info</a>
                    </li>

                    <li className="nav-item">
                        <a className="nav-link " href="#" onClick={(e)=>goTables(e)}>TablePlaces</a>
                    </li>
                </ul>
                <form className="form-inline my-2 my-lg-0">
                        <button className="btn btn-outline-danger my-2 my-sm-0" type="submit" onClick={()=>logout()}>Logout</button>
                </form>
            </div>
        </nav>
    );
}
export default HeaderComponent;
