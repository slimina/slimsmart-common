package cn.slimsmart.common.random.support;

import java.security.MessageDigest;

import org.opensaml.artifact.SAMLArtifactType0001;
import org.opensaml.artifact.SAMLArtifactType0002;
import org.opensaml.artifact.URI;

class SamlCompliantUniqueGenerator implements UniqueGenerator{

    private final byte[] sourceIdDigest;

    private final String sourceLocation;

    private boolean saml2compliant;

    private final DefaultRandomStringGenerator randomStringGenerator = new DefaultRandomStringGenerator(20);

    public SamlCompliantUniqueGenerator(final String sourceId,final boolean saml2compliant) {
        this.sourceLocation = sourceId;
        this.saml2compliant = saml2compliant;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(sourceId.getBytes("8859_1"));
            this.sourceIdDigest = messageDigest.digest();
        } catch (final Exception e) {
            throw new IllegalStateException("Exception generating digest which should not happen...EVER");
        }
    }

    /**
     * We ignore prefixes for SAML compliance.
     */
    @Override
    public String getRandomString(final String prefix) {
        if (saml2compliant) {
            return new SAMLArtifactType0002(this.randomStringGenerator.getNewStringAsBytes(), new URI(this.sourceLocation)).encode();
        } else {
            return new SAMLArtifactType0001(this.sourceIdDigest, this.randomStringGenerator.getNewStringAsBytes()).encode();
        }
    }

    public void setSaml2compliant(final boolean saml2compliant) {
        this.saml2compliant = saml2compliant;
    }
}
