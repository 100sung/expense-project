import { useState } from "react";
import { Expenselist } from "./component";

const App = () => {

    const [shouldRender, setShouldRender] = useState(true);

    return(
        <>
            <h1></h1>
            <h2>Expense Form</h2>
            <Expenselist />
        </>
    );

}

export default App;
