$('#ajax-register-form').bootstrapValidator({
	//        live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		nome : {
			validators : {
				notEmpty : {
					message : 'Inserisci nome'
				}
			}
		},
		cognome : {
			validators : {
				notEmpty : {
					message : 'Inserisci cognome'
				}
			}
		},
		email : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email'
				},
				regexp : {
					regexp : '^[a-zA-Z0-9.]+\@studenti\.unisa\.it',
					message : 'Email non valida'
				},
				emailAddress : {
					message : ' '
				}
			}
		},
		password : {
			validators : {
				notEmpty : {
					message : 'Inserisci la password'
				}
			}
		},
		confirmpassword : {
			validators : {
				notEmpty : {
					message : 'Inserisci conferma password'
				},
				identical : {
					field : 'password',
					message : 'La password è diversa'
				}
			}
		}
	}
});

$('#ajax-login-form').bootstrapValidator({
	//        live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		emailLogin : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email'
				},
				regexp : {
					regexp : '^[a-zA-Z0-9.]+\@studenti\.unisa\.it',
					message : 'Email non valida'
				}
			}
		},
		passwordLogin : {
			validators : {
				notEmpty : {
					message : 'Inserisci la password'
				}
			}
		}
	}
});
