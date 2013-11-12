package org.rapla.client;

import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.swing.ImageIcon;

import org.rapla.AppointmentFormaterImpl;
import org.rapla.components.util.Cancelable;
import org.rapla.components.util.Command;
import org.rapla.components.util.CommandScheduler;
import org.rapla.components.util.xml.RaplaNonValidatedInput;
import org.rapla.components.xmlbundle.I18nBundle;
import org.rapla.entities.RaplaType;
import org.rapla.entities.configuration.RaplaConfiguration;
import org.rapla.entities.domain.Appointment;
import org.rapla.entities.domain.AppointmentFormater;
import org.rapla.entities.storage.internal.SimpleIdentifier;
import org.rapla.facade.ClientFacade;
import org.rapla.facade.RaplaComponent;
import org.rapla.facade.internal.FacadeImpl;
import org.rapla.framework.RaplaDefaultContext;
import org.rapla.framework.RaplaException;
import org.rapla.framework.RaplaLocale;
import org.rapla.framework.SimpleProvider;
import org.rapla.framework.logger.NullLogger;
import org.rapla.storage.UpdateEvent;
import org.rapla.storage.dbrm.ConnectorFactory;
import org.rapla.storage.dbrm.EntityList;
import org.rapla.storage.dbrm.RemoteMethodCaller;
import org.rapla.storage.dbrm.RemoteOperator;
import org.rapla.storage.dbrm.RemoteServer;
import org.rapla.storage.dbrm.RemoteStorage;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;

public class RaplaGWTClient {

	final RaplaDefaultContext context = new RaplaDefaultContext();
	
	public RaplaGWTClient() throws RaplaException {
		final org.rapla.framework.logger.Logger logger = new NullLogger();
		final RaplaConfiguration config = new RaplaConfiguration("remote");
		final GWTRaplaLocale raplaLocale = new GWTRaplaLocale();
		I18nBundle i18n = new I18nBundle() {
			
			@Override
			public String getString(String key) throws MissingResourceException {
				return key;
			}
			
			@Override
			public Locale getLocale() {
				return null;
			}
			
			@Override
			public String getLang() {
				return "en";
			}
			
			@Override
			public ImageIcon getIcon(String key) throws MissingResourceException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String format(String key, Object[] obj)
					throws MissingResourceException {
				return key;
			}
			
			@Override
			public String format(String key, Object obj1, Object obj2)
					throws MissingResourceException {
				return key;
			}
			
			@Override
			public String format(String key, Object obj1)
					throws MissingResourceException {
				return key;
			}
		};
		final CommandScheduler commandQueue = new CommandScheduler() {
			
			@Override
			public Cancelable schedule(final Command command, long delay, long period) {
				RepeatingCommand cmd = new RepeatingCommand() {
	
				    @Override
				    public boolean execute() {
				        try {
							//command.execute();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        return true;
				    }
				};
				if ( period > 0)
				{
					Scheduler.get().scheduleFixedPeriod(cmd, (int)period);
				}
				else
				{
					Scheduler.get().scheduleFixedDelay(cmd, (int)delay);
				}
				
				return new Cancelable() {
					
					public void cancel() {
					}
				};
			}
			
			@Override
			public Cancelable schedule(Command command, long delay) {
				return schedule(command, delay, -1);
			}
			
		};
		RaplaNonValidatedInput raplaParser = new RaplaXMLSAXParser();
		context.put( org.rapla.framework.logger.Logger.class, logger);
		context.put( RaplaLocale.class, raplaLocale);
		context.put( RaplaNonValidatedInput.class, raplaParser);
		context.put( ConnectorFactory.class, new GWTConnectorFactory("@doc.version@"));
		context.put( CommandScheduler.class, commandQueue);
		context.put( RaplaComponent.RAPLA_RESOURCES, i18n);
		final SimpleProvider<RemoteMethodCaller> callerProvider = new SimpleProvider<RemoteMethodCaller>();
		final RemoteServer remoteServer = new RemoteServer() {
			public Object call(String methodName,Class<?>[] parameterTypes, Class<?> returnType ,Object[] args) throws RaplaException
			{
				RemoteMethodCaller remoteMethodCaller = callerProvider.get();
				return remoteMethodCaller.call(RemoteServer.class, methodName, parameterTypes, returnType, args);
			}
			
			@Override
			public void logout() throws RaplaException {
				call("logout", null, null, null);
			}
			
			@Override
			public String login(String username, String password, String connectAs)
					throws RaplaException {
				String result = (String) call("login", new Class[] {String.class, String.class, String.class}, null, new Object[] {username, password, connectAs});
				return result;
			}
			
		};
		final RemoteStorage remoteStorage = new RemoteStorage() {
			public Object call(String methodName,Class<?>[] parameterTypes, Class<?> returnType ,Object[] args) throws RaplaException
			{
				RemoteMethodCaller remoteMethodCaller = callerProvider.get();
				return remoteMethodCaller.call(RemoteStorage.class, methodName, parameterTypes, returnType, args);
			}
			
			public void restartServer() throws RaplaException {
				call("restartServer",null,null, null);
			}
			
			@Override
			public UpdateEvent refresh(String clientRepoVersion) throws RaplaException {
				return (UpdateEvent) call("refresh", new Class[] {String.class}, UpdateEvent.class, new Object[] {clientRepoVersion});
			}
			
			@Override
			public String getServerTime() throws RaplaException {
				return (String) call("getServerTime", null, String.class, null);
				
			}
			
			@Override
			public EntityList getResources() throws RaplaException {
				return (EntityList) call("getResources", null, EntityList.class, null);
			}
			
			@Override
			public EntityList getReservations(SimpleIdentifier[] allocatableIds,
					Date start, Date end) throws RaplaException {
				return (EntityList) call("getReservations", new Class[] { SimpleIdentifier[].class, Date.class, Date.class}, EntityList.class, new Object[] { allocatableIds, start, end});
			}
			
			@Override
			public Integer[][] getFirstAllocatableBindings(
					SimpleIdentifier[] allocatables, Appointment[] appointments, SimpleIdentifier[] reservationIds)
					throws RaplaException {
				return (Integer[][]) call("getFirstAllocatableBindings", new Class[] { SimpleIdentifier[].class, Appointment[].class,SimpleIdentifier[].class}, Integer[][].class, new Object[] { allocatables, appointments, reservationIds});
			}
			
			@Override
			public EntityList getEntityRecursive(SimpleIdentifier... id)
					throws RaplaException {
				return (EntityList) call("getEntityRecursive", new Class[] { SimpleIdentifier[].class}, EntityList.class, new Object[] { id});
			}
			
			@Override
			public EntityList getConflicts() throws RaplaException {
				return (EntityList) call("getConflicts", null, EntityList.class, null);
			}
			
			@Override
			public EntityList getAllAllocatableBindings(
					SimpleIdentifier[] allocatables, Appointment[] appointments, SimpleIdentifier[] reservationIds)
					throws RaplaException {
				return (EntityList) call("getAllAllocatableBindings", new Class[] { SimpleIdentifier[].class, Appointment[].class, SimpleIdentifier[].class}, EntityList.class, new Object[] { allocatables, appointments, reservationIds});
	
			}
			
			@Override
			public UpdateEvent dispatch(UpdateEvent event) throws RaplaException {
				return (UpdateEvent) call("dispatch", new Class[] {UpdateEvent.class}, UpdateEvent.class, new Object[] {event});
			}
			
			@Override
			public SimpleIdentifier[] createIdentifier(RaplaType raplaType, int count)
					throws RaplaException {
				return (SimpleIdentifier[]) call("createIdentifier", new Class[] {RaplaType.class,int.class}, SimpleIdentifier[].class, new Object[] {raplaType, count});
			}
			
			@Override
			public void confirmEmail(String username, String newEmail)
					throws RaplaException {
				call("confirmEmail", new Class[] {String.class, String.class}, null, new Object[] {username, newEmail});
							
			}
			
			@Override
			public void changePassword(String username, String oldPassword,
					String newPassword) throws RaplaException {
				call("changePassword", new Class[] {String.class, String.class, String.class}, null, new Object[] {username, oldPassword, newPassword});
	
			}
			
			@Override
			public void changeName(String username, String newTitle,
					String newSurename, String newLastname) throws RaplaException {
				call("changeName", new Class[] {String.class, String.class, String.class, String.class}, null, new Object[] {username, newTitle, newSurename,newLastname});
			}
			
			@Override
			public void changeEmail(String username, String newEmail)
					throws RaplaException {
				call("changeEmail", new Class[] {String.class, String.class}, null, new Object[] {username, newEmail});
				
			}
			
			@Override
			public boolean canChangePassword() throws RaplaException {
				return 	(Boolean) call("canChangePassword", null, null, null);
			}
			
			@Override
			public void authenticate(String username, String password)
					throws RaplaException {
				call("authenticate", new Class[] {String.class, String.class}, null, new Object[] {username, password});
				
			}

			@Override
			public void logEntityNotFound(String logMessage,SimpleIdentifier... referencedIds) throws RaplaException 
			{
				call("logEntityNotFound", new Class[] {String.class, SimpleIdentifier[].class}, null, new Object[] {logMessage, referencedIds});
			}

			@Override
			public Date getNextAllocatableDate(
					SimpleIdentifier[] allocatableIds, Appointment appointment,
					SimpleIdentifier[] reservationIds,
					Integer worktimeStartMinutes, Integer worktimeEndMinutes,
					Integer[] excludedDays, Integer rowsPerHour)
					throws RaplaException {
				return (Date) call("getNextAllocatableDate", new Class[] {SimpleIdentifier[].class, Appointment.class,SimpleIdentifier[].class,
						Integer.class, Integer.class,
						Integer[].class, Integer.class}, Date.class, new Object[] {allocatableIds, appointment,reservationIds, worktimeStartMinutes, worktimeEndMinutes, excludedDays, rowsPerHour});
			}
		};
		final RemoteOperator remoteOperator = new RemoteOperator(context, logger, config, remoteServer, remoteStorage);
		callerProvider.setValue( remoteOperator);
		FacadeImpl facade = new FacadeImpl(context, remoteOperator, logger);
		context.put(ClientFacade.class, facade);
		AppointmentFormater appointmentFormater = new AppointmentFormaterImpl(context);
		context.put( AppointmentFormater.class, appointmentFormater);
	}
	
	public RaplaDefaultContext getContext() 
	{
		return context;
	}

}
