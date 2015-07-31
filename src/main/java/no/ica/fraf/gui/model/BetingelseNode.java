package no.ica.fraf.gui.model;

import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import no.ica.fraf.model.AvdelingBetingelse;

/**
 * Klasse som brukes til node i tremodell for andre betingelser (JTreeTable)
 * 
 * @author abr99
 * 
 */
public class BetingelseNode {
    /**
     * Barn av denn noden
     */
    private Vector children;

    /**
     * Foreldre til denne noden
     */
    private BetingelseNode parent;

    /**
     * Barn
     */
    private Object[] objectChildren;

    /**
     * Betingelse for noden
     */
    private AvdelingBetingelse avdelingBetingelse;

    /**
     * Rottekst
     */
    private String rootText;

    /**
     * Alle noder
     */
    private Hashtable allNodes;

    /**
     * Konstruktør
     * 
     * @param aRootText
     * @param nodes
     */
    public BetingelseNode(String aRootText, Hashtable nodes) {
        rootText = aRootText;
        allNodes = nodes;

    }

    /**
     * Konstruktør
     * 
     * @param betingelse
     * @param someChildren
     */
    public BetingelseNode(AvdelingBetingelse betingelse, Vector someChildren) {
        avdelingBetingelse = betingelse;
        children = someChildren;
    }

    /**
     * Konstruktør
     * 
     * @param aParent
     * @param someChildren
     */
    public BetingelseNode(BetingelseNode aParent, Vector someChildren) {
        parent = aParent;
        children = someChildren;
    }

    /**
     * Hent barn
     * 
     * @return barn
     */
    public Vector getVectorChildren() {
        objectChildren = null;
        return children;
    }

    /**
     * Henter ut sti til node
     * 
     * @return sti
     */
    public BetingelseNode[] getPath() {
        return getPathToRoot(this, 0);
    }

    /**
     * Finner sti for node
     * 
     * @param aNode
     * @param depth
     * @return sti
     */
    protected BetingelseNode[] getPathToRoot(BetingelseNode aNode, int depth) {
        BetingelseNode[] retNodes;

        if (aNode == null) {
            if (depth == 0)
                return null;
            retNodes = new BetingelseNode[depth];
        } else {
            depth++;
            retNodes = getPathToRoot(aNode.getParent(), depth);
            retNodes[retNodes.length - depth] = aNode;
        }
        return retNodes;
    }

    /**
     * Hent foreldre
     * 
     * @return foreldre
     */
    public BetingelseNode getParent() {
        return parent;
    }

    /**
     * Er node rotnode
     * 
     * @return true dersom rotnode ellers false
     */
    public boolean isRoot() {
        if (rootText != null) {
            return true;
        }
        return false;
    }

    /**
     * Henter betingelse for node
     * 
     * @return betingelse
     */
    public AvdelingBetingelse getAvdelingBetingelse() {
        return avdelingBetingelse;
    }

    /**
     * Streng som skal vises i node
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (rootText != null) {
            return rootText;
        }
        if(avdelingBetingelse.getBetingelseType().getBetingelseNavn() != null){
        return avdelingBetingelse.getBetingelseType().getBetingelseNavn();
        }
        return avdelingBetingelse.getBetingelseType().getBetingelseTypeKode();
    }

    /**
     * Henter barn
     * 
     * @return barn
     */
    @SuppressWarnings("unchecked")
    public Object[] getChildren() {
        if (objectChildren != null) {
            return objectChildren;
        }

        if (rootText != null) {
            Set<AvdelingBetingelse> keySet = allNodes.keySet();
            Vector childrenVector;
            objectChildren = new BetingelseNode[allNodes.size()];
            int i = 0;

            for (AvdelingBetingelse tmpAvdelingBetingelse : keySet) {
                childrenVector = (Vector) allNodes.get(tmpAvdelingBetingelse);
                objectChildren[i] = new BetingelseNode(tmpAvdelingBetingelse,
                        childrenVector);
                i++;
            }
        } else if (children != null) {

            objectChildren = new BetingelseNode[children.size()];

            for (int i = 0; i < children.size(); i++) {
                objectChildren[i] = new BetingelseNode(
                        (AvdelingBetingelse) children.get(i), null);
            }

        }
        return objectChildren;

    }
}
