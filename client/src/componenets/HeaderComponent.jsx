import React, {Component} from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)


    }

    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                        aria-label="Toggle navigation">
                </button>
                <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                    {/*<a className="navbar-brand" href="#">User Control</a>*/}
                    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">

                    </ul>
                    <form className="form-inline my-2 my-lg-0">
                        <a href="/homepagee" className="navbar-toggler-icon"></a>
                    </form>
                </div>
            </nav>
        );
    }
}

export default HeaderComponent;