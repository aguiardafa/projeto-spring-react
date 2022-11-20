import logo from '../../assets/img/logo.svg';

import './styles.css';

function Header() {
    return (
        <>
            <header>
                <div className="dsmeta-logo-container">
                    <img src={logo} alt="DSMeta" />
                    <h1>Gerenciador de Vendas</h1>
                    <p>
                        Desenvolvido por <b><a href="https://www.instagram.com/devsuperior.ig">@devsuperior.ig</a></b> e customizado por <b><a href="https://github.com/aguiardafa">@aguiardafa</a></b>
                    </p>
                </div>
            </header>
        </>
    )
}

export default Header;
