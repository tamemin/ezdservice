mainApp
		.factory(
				'caUtils',
				function($http, $window) {
					var caUtils = {};

					caUtils.supportedEcdsaCurves = [ 'prime192v1',
							'prime192v2', 'prime192v3', 'prime239v1',
							'prime239v2', 'prime239v3', 'prime256v1',
							'secp192k1', 'secp192r1', 'secp224k1', 'secp224r1',
							'secp256k1', 'secp256r1', 'secp384r1', 'secp521r1',
							'P-224', 'P-256', 'P-384', 'P-521', 'c2pnb163v1',
							'c2pnb163v2', 'c2pnb163v3', 'c2pnb176w1',
							'c2tnb191v1', 'c2tnb191v2', 'c2tnb191v3',
							'c2pnb208w1', 'c2tnb239v1', 'c2tnb239v2',
							'c2tnb239v3', 'c2pnb272w1', 'c2pnb304w1',
							'c2tnb359v1', 'c2pnb368w1', 'c2tnb431r1',
							'sect163k1', 'sect163r1', 'sect163r2', 'sect193r1',
							'sect193r2', 'sect233k1', 'sect233r1', 'sect239k1',
							'sect283k1', 'sect283r1', 'sect409k1', 'sect409r1',
							'sect571k1', 'sect571r1', 'B-163', 'B-233',
							'B-283', 'B-409', 'B-571', 'brainpoolp160r1',
							'brainpoolp160t1', 'brainpoolp192r1',
							'brainpoolp192t1', 'brainpoolp224r1',
							'brainpoolp224t1', 'brainpoolp256r1',
							'brainpoolp256t1', 'brainpoolp320r1',
							'brainpoolp320t1', 'brainpoolp384r1',
							'brainpoolp384t1', 'brainpoolp512r1',
							'brainpoolp512t1' ];

					caUtils.generateEcdsaKey = function(curve, onSuccess) {
						var data = JSON.stringify({

							'curve' : curve

						});
						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http
								.post(
										'http://localhost:8005/caserver/keys/ecdsa/generate',
										data, config).success(
										function(response, status, headers,
												config) {
											onSuccess(response.data);
										}).error(
										function(response, status, header,
												config) {
											$window.alert(""
													+ response.error.message
													+ "\n"
													+ response.error.detail);
										});
					};

					caUtils.generateRsaKey = function(keyLength, onSuccess) {
						var data = JSON.stringify({

							'keyLength' : keyLength

						});
						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http
								.post(
										'http://localhost:8005/caserver/keys/rsa/generate',
										data, config).success(
										function(response, status, headers,
												config) {
											onSuccess(response.data);
										}).error(
										function(response, status, header,
												config) {
											$window.alert(""
													+ response.error.message
													+ "\n"
													+ response.error.detail);
										});
					};

					caUtils.generateCsr = function(data, onSuccess) {

						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http.post(
								'http://localhost:8005/caserver/csr/generate',
								data, config).success(
								function(response, status, headers, config) {
									onSuccess(response.data);
								}).error(
								function(response, status, header, config) {
									$window.alert("" + response.error.message
											+ "\n" + response.error.detail);
								});
					};

					caUtils.createIA = function(data, onSuccess) {

						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http
								.post(
										'http://localhost:8005/caserver/issuingAuthority/create',
										data, config)
								.success(
										function(response, status, headers,
												config) {
											self.responseEntityId = response.data.id;
											onSuccess();
										}).error(
										function(response, status, header,
												config) {
											$window.alert(""
													+ response.error.message
													+ "\n"
													+ response.error.detail);
										});
					};

					caUtils.createIAFromCsr = function(data, onSuccess) {

						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						};
						$http
								.post(
										'http://localhost:8005/caserver/issuingAuthority/createFromCsr',
										data, config)
								.success(
										function(response, status, headers,
												config) {
											self.responseEntityId = response.data.id;
											onSuccess();
										}).error(
										function(response, status, header,
												config) {
											$window.alert(""
													+ response.error.message
													+ "\n"
													+ response.error.detail);
										});
					};

					caUtils.createEndEntity = function(data, onSuccess) {

						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http
								.post(
										'http://localhost:8005/caserver/endEntity/create',
										data, config)
								.success(
										function(response, status, headers,
												config) {
											self.responseEntityId = response.data.id;
											onSuccess();
										}).error(
										function(response, status, header,
												config) {
											$window.alert(""
													+ response.error.message
													+ "\n"
													+ response.error.detail);
										});
					};

					caUtils.createEndEntityFromCsr = function(data, onSuccess) {

						var config = {
							headers : {
								'Content-Type' : 'application/json'
							}
						}
						$http
								.post(
										'http://localhost:8005/caserver/endEntity/createFromCsr',
										data, config)
								.success(
										function(response, status, headers,
												config) {
											self.responseEntityId = response.data.id;
											onSuccess();
										}).error(
										function(response, status, header,
												config) {
											$window.alert(""
													+ response.error.message
													+ "\n"
													+ response.error.detail);
										});
					};

					return caUtils;
				});
