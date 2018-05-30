package es.gob.afirma.standalone.plugins;

import java.security.cert.Certificate;

import es.gob.afirma.core.signers.AOSignConstants;

/**
 * Plugin que aporta informaci&oacute;n adicional a la aplicaci&oacute;n.
 */
public abstract class AfirmaPlugin {

	private PluginInfo info = null;

	/**
	 * Proporciona la informaci&oacute;n b&aacute;sica de la aplicaci&oacute;n.
	 * @return Informaci&oacute;n del plugin.
	 */
	public final PluginInfo getInfo() {
		return this.info;
	}

	/**
	 * Establece la informaci&oacute;n b&aacute;sica de la aplicaci&oacute;n.
	 * @param info Informaci&oacute;n del plugin.
	 */
	public final void setInfo(PluginInfo info) {
		this.info = info;
	}

	/**
	 * Proceso ejecutado sobre los datos antes de firma.
	 * @param data Datos que se van a firmar.
	 * @param format Formato de firma que se aplicara sobre los datos. Los
	 * posibles formatos de firma se definen en {@link AOSignConstants}.
	 * @return Datos ya procesados que se van a firmar.
	 * @throws PluginControlledException Cuando se produce un error en el procesado
	 * de los datos.
	 */
	@SuppressWarnings("static-method")
	public byte [] preSignProcess(byte[] data, String format)
			throws PluginControlledException {
		return data;
	}

	/**
	 * Proceso ejecutado sobre las firmas generadas.
	 * @param signature Firma electr&oacute;nica generada.
	 * @param format Formato de la firma. Los posibles formatos de firma se
	 * definen en {@link AOSignConstants}.
	 * @param certChain Cadena de certificaci&oacute;n usada en al firma.
	 * @return Firma ya posprocesada.
	 * @throws PluginControlledException Cuando se produce un error en el procesado
	 * de la firma.
	 */
	@SuppressWarnings("static-method")
	public byte [] postSignProcess(byte[] signature, String format, Certificate[] certChain)
			throws PluginControlledException {
		return signature;
	}

	/**
	 * Proceso ejecutado al instalar el plugin en AutoFirma. Esto s&oacute;lo se
	 * ejecutar&aacute; una vez a lo largo de vida del plugin. Este proceso s&oacute;lo
	 * tendr&aacute; permisos de administrador si el usuario ejecut&oacute; Autofirma
	 * con estos.
	 * @throws PluginControlledException Cuando ocurre un error durante el proceso.
	 */
	public void install() throws PluginControlledException {
		// Por defecto, no se hace nada
	}

	/**
	 * Proceso ejecutado al desinstalar el plugin de AutoFirma. Este proceso deber&iacute;
	 * eliminar cualquier resto o referencia que se haya dejado en el sistema como parte del
	 * proceso de instalaci&oacute;n o de firma.
	 * @throws PluginControlledException Cuando ocurre un error durante el proceso.
	 */
	public void uninstall() throws PluginControlledException {
		// Por defecto, no se hace nada
	}

	/**
	 * Proceso ejecutado al finalizar un proceso de firma completo. En &eacute;l se puede
	 * configurar lo necesario para restaurar el estado del plugin antes de iniciar una
	 * nueva operaci&oacute;n.
	 */
	public void reset() {
		// No se hace nada
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj != null && obj instanceof AfirmaPlugin) {
			final PluginInfo myInfo = getInfo();
			final PluginInfo objInfo = ((AfirmaPlugin) obj).getInfo();
			return myInfo.getInternalName().equals(objInfo.getInternalName()) &&
					myInfo.getVersionCode() == objInfo.getVersionCode();
		}
		return super.equals(obj);
	}

	@Override
	public final int hashCode() {
		return super.hashCode();
	}

	@Override
	public final String toString() {
		return this.info != null ? this.info.toString() : super.toString();
	}

}
