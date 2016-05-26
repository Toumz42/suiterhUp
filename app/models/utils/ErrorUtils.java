package models.utils;

/**
 * Created with IntelliJ IDEA.
 * User: obal
 * Date: 26/05/14
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public class ErrorUtils
{
    public static final String	APPLICATION_MODIFIER_MDP_OK	= "Votre nouveau mot de passe a bien été pris en compte.";
    public static final String	APPLICATION_MODIFIER_MDP_KO	= "Votre nouveau mot de passe a bien été pris en compte.";

    public static final String	GED_SAVE					= "Le ged a bien été créé.";
    public static final String	GED_UPDATE					= "Le ged a bien été mis à jour.";
    public static final String	GED_DELETE					= "Le ged a bien été supprimé.";

    public static final String	OPTIONS_UPDATE				= "L'option a bien été mise à jour.";
    public static final String	OPTIONS_DELETE				= "L'option a bien été supprimée.";

    public static final String	CMD_SAVE					= "La commande a été crée";
    public static final String	CMD_DELETE					= "La commande a été supprimée";

    public static final String	NOCONNECTED					= "Vous n'êtes pas connecté, veuillez vous identifier";

    public static final String	REFERENTIEL_DELETE			= "Le référentiel a été supprimé";

    public static final String	CONVENTION_DELETE			= "La convention a été supprimée";


    private boolean				isError;
    private String				messageRetour;
    private String				gravite;
    private Long				id;

    public ErrorUtils()
    {
    }

    /**
     * Permet de creer un enregistrement d'une erreur renvoyée via json
     *
     * @param isError
     *            : true = erreur, false = info ou message de retour à afficher
     * @param messageRetour
     *            : message à afficher coté client
     * @param gravite
     *            : niveau de gravité de l'erreur (info, warn ou erreur)
     */
    public static ErrorUtils createError(boolean isError, String messageRetour, String gravite )
    {
        ErrorUtils e = new ErrorUtils();
        e.isError = isError;
        e.messageRetour = messageRetour;
        e.gravite = gravite;
        return e;
    }

    public boolean isError()
    {
        return isError;
    }

    public void setError( boolean isError )
    {
        this.isError = isError;
    }

    public String getMessageRetour()
    {
        return messageRetour;
    }

    public void setMessageRetour( String messageRetour )
    {
        this.messageRetour = messageRetour;
    }

    public String getGravite()
    {
        return gravite;
    }

    public void setGravite( String gravite )
    {
        this.gravite = gravite;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

}
