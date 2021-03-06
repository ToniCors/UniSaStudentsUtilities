<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,gestioneUtente.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	Utente utente = (Utente) session.getAttribute("utente");
	if (utente == null) {
		// SIMULA LA SESSIONE
		//utente = new Utente("a","a","a","a",true,true);

	} else {

	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>Student Utilities</title>

  <%@ include file="-UPimport.html"%>


</head>

<body>
  <div class="topbar animated fadeInLeftBig"></div>
 	
	<%@ include file="-menuLogged.html"%>


  <!-- works -->

  <div id="works"  class=" clearfix grid">
    <div class="container col-lg-8 col-md-offset-2">
      <h2 style="text-align: left;">Inserisci annuncio</h2> 

      <!-- TabellaLibri -->
      <table class="table">
        <tbody>
          <tr>
            <td>
            <img src="images/is.jpeg" width="199" height="283"> <br><br>
                          <label class="btn btn-file btn-success"><span class="glyphicon glyphicon-open"></span>
                Image...<input type="file" style="display: none;">
              </label>
            </td>
            <td>
              <ul class="list-unstyled stylish-insert-ad">
                <li><input type="text" name="titolo-libro" class="form-control" placeholder="Titolo" tabindex="1"></li>
                <li><input type="text" name="autore-libro" class="form-control" placeholder="Autore" tabindex="2"></li>
                <li>
                  <select class="form-control" name="select-condizioni" id="select-condizioni" tabindex="3">
                    <option selected>Seleziona condizione</option>
                    <option>Nuovo</option>
                    <option>Usato</option>
                    <option>Fotocopie</option>
                  </select>
                </li>
                <li><input type="number" min="00.00" value="00.00" step="01.00" name="prezzo-libro" class="form-control" placeholder="Prezzo" tabindex="4"></li>
                <li>
                  <select class="form-control" id="select-corso" tabindex="5">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                  </select>
                </li>
                <li>
                  <textarea id="textarea-libro" class="form-control" placeholder="Descrizione" tabindex="6" rows="6"></textarea>
                </li>
              </ul>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="form-group pull-right" style="margin-top: -29px">
        <input type="button" name="inserisci-annuncio" value="inserisci annuncio" class="btn btn-success">
      </div>
      <!-- /TabellaLibri -->
    </div>
  </div>
  <!-- works -->

	<%@ include file="-footer.html"%>

	<%@ include file="-DOWNimport.html"%>
	
</body>
</html>
