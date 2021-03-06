/**
 *
 * $Id$
 */
package dart.validation;

import dart.Metadata;
import dart.Parameter;
import dart.Type;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link dart.Typedef}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface TypedefValidator {
	boolean validate();

	boolean validateDocumentation(String value);
	boolean validateName(String value);
	boolean validateType(Type value);
	boolean validateParameters(EList<Parameter> value);
	boolean validateMetadata(EList<Metadata> value);
}
