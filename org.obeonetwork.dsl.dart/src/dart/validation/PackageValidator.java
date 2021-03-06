/**
 *
 * $Id$
 */
package dart.validation;

import dart.Project;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link dart.Package}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface PackageValidator {
	boolean validate();

	boolean validateName(String value);
	boolean validateLicense(String value);
	boolean validateDependencies(EList<dart.Package> value);
	boolean validateProject(Project value);
}
